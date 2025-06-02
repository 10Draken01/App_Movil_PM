package com.draken.app_movil_pm.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
    private var _emailText = MutableStateFlow("")
    var emailText: StateFlow<String> = _emailText

    private var _passwordText = MutableStateFlow("")
    var passwordText: StateFlow<String> = _passwordText

    fun onChangeEmailText(text: String){
        _emailText.value = text
    }

    fun onChangePasswordText(text: String){
        _passwordText.value = text
    }
}