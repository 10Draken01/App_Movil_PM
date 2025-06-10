package com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.features.agregar_cliente.domain.usecase.AgregarClienteUseCase
import com.draken.app_movil_pm.features.login.domain.usecase.LoginUseCase

class AgregarClienteViewModelFactory(
    private val useCase: AgregarClienteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AgregarClienteViewModel(useCase) as T
    }
}