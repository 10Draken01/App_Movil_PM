package com.draken.app_movil_pm.features.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.features.register.domain.usecase.RegisterUseCase

class RegisterViewModelFactory(
    private val useCase: RegisterUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(useCase) as T
    }
}