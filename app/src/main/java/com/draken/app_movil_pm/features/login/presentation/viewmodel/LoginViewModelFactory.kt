package com.draken.app_movil_pm.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase

class LoginViewModelFactory(
    private val useCase: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(useCase) as T
    }
}