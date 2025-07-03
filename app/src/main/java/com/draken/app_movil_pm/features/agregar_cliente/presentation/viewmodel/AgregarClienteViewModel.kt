package com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Character_Icon
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.InputType
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.Response
import com.draken.app_movil_pm.features.agregar_cliente.domain.usecase.AgregarClienteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgregarClienteViewModel(
    private val agregarClienteUseCase: AgregarClienteUseCase
) : ViewModel() {

    private val _claveClienteText = MutableStateFlow("")
    val claveClienteText: StateFlow<String> = _claveClienteText.asStateFlow()

    private val _nombreText = MutableStateFlow("")
    val nombreText: StateFlow<String> = _nombreText.asStateFlow()

    private val _celularText = MutableStateFlow("")
    val celularText: StateFlow<String> = _celularText.asStateFlow()

    private val _emailText = MutableStateFlow("")
    val emailText: StateFlow<String> = _emailText.asStateFlow()

    private val _characterIcon = MutableStateFlow<Character_Icon>(Character_Icon())
    val characterIcon: StateFlow<Character_Icon> = _characterIcon.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _stateResponse = MutableStateFlow(Response())
    val stateResponse: StateFlow<Response> = _stateResponse.asStateFlow()

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

    val inputs: List<InputType>
        get() = listOf(
        InputType(
            label = "Clave Cliente",
            placeholder = "000000001",
            value = _claveClienteText.value,
            onChange = { onChangeClaveClienteText(it) }
        ),
        InputType(
            label = "Nombre",
            placeholder = "Ms. Amanda Osinski IV",
            value = _nombreText.value,
            onChange = { onChangeNombreText(it) }
        ),
        InputType(
            label = "Celular",
            placeholder = "+1-270-210-3231",
            value = _celularText.value,
            onChange = { onChangeCelularText(it) }
        ),
        InputType(
            label = "Email",
            placeholder = "mallie@gmail.com",
            value = _emailText.value,
            onChange = { onChangeEmailText(it) }
        ),
    )

    fun agregar(){
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

                val result: Response = agregarClienteUseCase(
                    _claveClienteText.value.trim(),
                    _nombreText.value,
                    _celularText.value.trim(),
                    _emailText.value.trim(),
                    _characterIcon.value
                )

                if( result.error.isNullOrEmpty()){
                    _stateResponse.value = Response(message = "${_nombreText.value} se agrego correctamente")
                } else {
                    _stateResponse.value = result
                }
            } catch (e: Exception) {
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

    fun onChangeClaveClienteText(text: String) {
        _claveClienteText.value = text
        clearErrorOnChangeValue()
    }

    fun onChangeNombreText(text: String) {
        _nombreText.value = text
        clearErrorOnChangeValue()
    }

    fun onChangeCelularText(text: String) {
        _celularText.value = text
        clearErrorOnChangeValue()
    }

    fun onChangeEmailText(text: String) {
        _emailText.value = text
        clearErrorOnChangeValue()
    }

    fun onChangeCharacterIconNumber(icon: Int) {
        _characterIcon.value = Character_Icon(character_icon_number = icon)
        clearErrorOnChangeValue()
    }

    fun onChangeCharacterIconUri(uri: Uri?) {
        _characterIcon.value = Character_Icon(character_icon_uri = uri)
        clearErrorOnChangeValue()
    }

    fun clearErrorOnChangeValue(){
        // Limpiar error cuando el usuario empiece a escribir
        if (_stateResponse.value.error != null) {
            _stateResponse.value = Response()
        }
    }

    fun clearError() {
        _stateResponse.value = Response()
    }
}