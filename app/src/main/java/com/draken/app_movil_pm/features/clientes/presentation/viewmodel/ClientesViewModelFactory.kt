package com.draken.app_movil_pm.features.clientes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.features.clientes.domain.usecase.GetClientesUseCase

class ClientesViewModelFactory(
    private val useCase: GetClientesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClientesViewModel(useCase) as T
    }
}