package com.draken.app_movil_pm.features.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel: ViewModel() {
    private var _usernameText = MutableStateFlow("")
    var usernameText: StateFlow<String> = _usernameText

    private var _emailText = MutableStateFlow("")
    var emailText: StateFlow<String> = _emailText

    private var _passwordText = MutableStateFlow("")
    var passwordText: StateFlow<String> = _passwordText

    private var _confirmPasswordText = MutableStateFlow("")
    var confirmPasswordText: StateFlow<String> = _confirmPasswordText

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