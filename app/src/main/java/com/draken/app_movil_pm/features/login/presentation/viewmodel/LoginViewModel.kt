package com.draken.app_movil_pm.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase
import com.draken.app_movil_pm.features.login.domain.model.Login
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailText = MutableStateFlow("")
    val emailText: StateFlow<String> = _emailText.asStateFlow()

    private val _passwordText = MutableStateFlow("")
    val passwordText: StateFlow<String> = _passwordText.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _stateResponse = MutableStateFlow(Login())
    val stateResponse: StateFlow<Login> = _stateResponse.asStateFlow()

    fun login(){
        // Limpiar error anterior
        _stateResponse.value = Login()

        if (!validateData()) {
            _stateResponse.value = Login(error = "El correo o contraseña no pueden estar vacíos")
            return
        }

        if (!isValidEmail(_emailText.value)) {
            _stateResponse.value = Login(error = "Por favor ingresa un correo electrónico válido")
            return
        }

        viewModelScope.launch {
            try {
                _loading.value = true

                val result = loginUseCase(_emailText.value.trim(), _passwordText.value.trim())
                _stateResponse.value = result

            } catch (e: Exception) {
                _stateResponse.value = Login(error = "Error de conexión. Inténtalo de nuevo.")
            } finally {
                _loading.value = false
            }
        }
    }

    private fun validateData(): Boolean {
        return _emailText.value.trim().isNotBlank() && _passwordText.value.isNotBlank()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    fun onChangeEmailText(text: String) {
        _emailText.value = text
        // Limpiar error cuando el usuario empiece a escribir
        if (_stateResponse.value.error != null) {
            _stateResponse.value = Login()
        }
    }

    fun onChangePasswordText(text: String) {
        _passwordText.value = text
        // Limpiar error cuando el usuario empiece a escribir
        if (_stateResponse.value.error != null) {
            _stateResponse.value = Login()
        }
    }

    fun clearError() {
        _stateResponse.value = Login()
    }
}