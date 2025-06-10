package com.draken.app_movil_pm.features.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draken.app_movil_pm.features.register.domain.model.ResponseRegister
import com.draken.app_movil_pm.features.register.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private var _usernameText = MutableStateFlow("")
    var usernameText: StateFlow<String> = _usernameText

    private var _emailText = MutableStateFlow("")
    var emailText: StateFlow<String> = _emailText

    private var _passwordText = MutableStateFlow("")
    var passwordText: StateFlow<String> = _passwordText

    private var _confirmPasswordText = MutableStateFlow("")
    var confirmPasswordText: StateFlow<String> = _confirmPasswordText

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _stateResponse = MutableStateFlow(ResponseRegister())
    val stateResponse: StateFlow<ResponseRegister> = _stateResponse.asStateFlow()

    fun register(){
        // Limpiar error anterior
        _stateResponse.value = ResponseRegister()

        if (!validateData()) {
            _stateResponse.value = ResponseRegister(error = "El correo o contraseña no pueden estar vacíos")
            return
        }

        if (!isValidEmail(_emailText.value)) {
            _stateResponse.value = ResponseRegister(error = "Por favor ingresa un correo electrónico válido")
            return
        }

        if(_passwordText.value != _confirmPasswordText.value){
            _stateResponse.value = ResponseRegister(error = "Las contraseñas no coinciden")
            return
        }

        viewModelScope.launch {
            try {
                _loading.value = true

                val result: ResponseRegister = registerUseCase(_usernameText.value.trim(),_emailText.value.trim(), _passwordText.value.trim())
                _stateResponse.value = result
            } catch (e: Exception) {
                _stateResponse.value = ResponseRegister(error = "Error de conexión. Inténtalo de nuevo.")
            } finally {
                _loading.value = false
            }
        }
    }
    private fun validateData(): Boolean {
        return _emailText.value.trim().isNotBlank() &&
                _usernameText.value.isNotBlank() &&
                _passwordText.value.isNotBlank() &&
                _confirmPasswordText.value.isNotBlank()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }
    fun onChangeUsernameText(text: String){
        _usernameText.value = text
    }

    fun onChangeEmailText(text: String){
        _emailText.value = text
    }

    fun onChangePasswordText(text: String){
        _passwordText.value = text
    }

    fun onChangeConfirmPasswordText(text: String){
        _confirmPasswordText.value = text
    }
}