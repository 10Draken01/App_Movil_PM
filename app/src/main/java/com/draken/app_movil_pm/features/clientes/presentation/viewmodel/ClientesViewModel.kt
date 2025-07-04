package com.draken.app_movil_pm.features.clientes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.features.clientes.domain.usecase.GetClientesUseCase
import kotlinx.coroutines.launch
import com.draken.app_movil_pm.context.UseDataContext
import com.draken.app_movil_pm.core.domain.model.Cliente

class ClientesViewModel(
    private val getClientesUseCase: GetClientesUseCase
) : ViewModel() {
    private val _clientes = MutableStateFlow<List<Cliente>>(emptyList())
    val clientes: StateFlow<List<Cliente>> = _clientes

    init {
        fetchCharacters(page = 1)
    }

    fun fetchCharacters(page: Int) {
        viewModelScope.launch {
            _clientes.value = getClientesUseCase(page = page)
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

    fun onChangeSearchText(text: String){
        _searchText.value = text
    }
}