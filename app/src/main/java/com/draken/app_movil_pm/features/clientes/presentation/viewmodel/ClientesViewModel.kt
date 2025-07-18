package com.draken.app_movil_pm.features.clientes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.draken.app_movil_pm.R
import kotlinx.coroutines.launch
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.rooms.domain.usecase.GetLocalClientesPageUseCase
import com.draken.app_movil_pm.features.clientes.domain.usecase.GetClientesPageUseCase

class ClientesViewModel(
    private val getClientesPageUseCase: GetClientesPageUseCase,
    private val getLocalClientesPageUseCase: GetLocalClientesPageUseCase,
    private val connectivityMonitorRepository: ConnectivityMonitorRepository
) : ViewModel() {
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected
    private val _clientes = MutableStateFlow<List<Cliente>>(emptyList())
    val clientes: StateFlow<List<Cliente>> = _clientes

    init {
        getClientesPage(page = 1)
    }

    fun getClientesPage(page: Int){

        _isConnected.value = connectivityMonitorRepository.isCurrentlyConnected()
        if (_isConnected.value) {
            fetchClientes(page = page)
        } else {
            getLocalClientesPage(page = page)
        }

    }

    private fun getLocalClientesPage(page: Int) {
        viewModelScope.launch {
            getLocalClientesPageUseCase(page = page).collect {
                _clientes.value = it.toDomain().map {
                    cliente ->
                    Cliente(
                        claveCliente = cliente.claveCliente,
                        nombre = cliente.nombre,
                        email = cliente.email,
                        celular = cliente.celular,
                        characterIcon = CharacterIcon(
                            characterIconNumber = icons[cliente.characterIcon.characterIconNumber],
                            characterIconUrl = cliente.characterIcon.characterIconUrl,
                            characterIconUri = cliente.characterIcon.characterIconUri
                        ),
                        isLocal = cliente.isLocal
                    )
                }
            }
        }
    }

    private fun fetchClientes(page: Int) {
        viewModelScope.launch {
            _clientes.value = getClientesPageUseCase(page = page).map {
                cliente ->
                Cliente(
                    claveCliente = cliente.claveCliente,
                    nombre = cliente.nombre,
                    email = cliente.email,
                    celular = cliente.celular,
                    characterIcon = CharacterIcon(
                        characterIconNumber = icons[cliente.characterIcon.characterIconNumber],
                        characterIconUrl = cliente.characterIcon.characterIconUrl,
                        characterIconUri = cliente.characterIcon.characterIconUri
                    )
                )
            }
        }
    }

    var icons: List<Int> = listOf(
        R.drawable.estrella,
        R.drawable.edificio,
        R.drawable.caja,
        R.drawable.etiqueta,
        R.drawable.sobre,
        R.drawable.reloj,
        R.drawable.laptop,
        R.drawable.grafica,
        R.drawable.maletin,
        R.drawable.portafolio
    )

    private var _searchText = MutableStateFlow("")
    var searchText: StateFlow<String> = _searchText

    private var _emailText = MutableStateFlow("")
    var emailText: StateFlow<String> = _emailText

    fun onChangeSearchText(text: String) {
        _searchText.value = text
    }
}