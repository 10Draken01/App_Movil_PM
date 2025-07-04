package com.draken.app_movil_pm.features.eliminar_cliente.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.eliminar_cliente.domain.model.InputType
import com.draken.app_movil_pm.features.eliminar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.eliminar_cliente.domain.usecase.EliminarClienteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EliminarClienteViewModel(
    private val eliminarClienteUseCase: EliminarClienteUseCase
) : ViewModel() {

    private val _claveClienteText = MutableStateFlow<String>("")
    val claveClienteText: StateFlow<String> = _claveClienteText.asStateFlow()

    private val _nombreText = MutableStateFlow<String>("")
    val nombreText: StateFlow<String> = _nombreText.asStateFlow()

    private val _celularText = MutableStateFlow<String>("")
    val celularText: StateFlow<String> = _celularText.asStateFlow()

    private val _emailText = MutableStateFlow<String>("")
    val emailText: StateFlow<String> = _emailText.asStateFlow()

    private val _characterIcon = MutableStateFlow<CharacterIcon>(CharacterIcon())
    val characterIcon: StateFlow<CharacterIcon> = _characterIcon.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _stateResponse = MutableStateFlow<Response>(Response())
    val stateResponse: StateFlow<Response> = _stateResponse.asStateFlow()

    fun setCliente(cliente: Cliente){
        _claveClienteText.value = cliente.claveCliente
        _nombreText.value = cliente.nombre
        _celularText.value = cliente.celular
        _emailText.value = cliente.email
        _characterIcon.value = cliente.characterIcon
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

    fun eliminarCliente(){
        // Limpiar error anterior
        _stateResponse.value = Response()

        if (!validateData()) {
            _stateResponse.value = Response(error = "El correo o contraseña no pueden estar vacíos")
            return
        }

        if (!isValidEmail(_emailText.value)) {
            _stateResponse.value = Response(error = "Por favor ingresa un correo electrónico válido")
            return
        }

        viewModelScope.launch {
            try {
                _loading.value = true

                val result: Response = eliminarClienteUseCase(
                    _claveClienteText.value.trim()
                )

                if( result.error.isNullOrEmpty()){
                    _stateResponse.value = Response(message = "${_nombreText.value} se elimino correctamente")
                } else {
                    _stateResponse.value = result
                }
            } catch (e: Exception) {
                Log.e("EliminarCliente", "Error: ${e.message}")
                _stateResponse.value = Response(error = "Error de conexión. Inténtalo de nuevo.")
            } finally {
                _loading.value = false
            }
        }
    }

    private fun validateData(): Boolean {
        return  _claveClienteText.value.isNotBlank() &&
                _nombreText.value.isNotBlank() &&
                _celularText.value.isNotBlank() &&
                _emailText.value.trim().isNotBlank()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }
}