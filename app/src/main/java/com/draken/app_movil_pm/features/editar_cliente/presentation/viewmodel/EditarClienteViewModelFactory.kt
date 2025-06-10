package com.draken.app_movil_pm.features.editar_cliente.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.features.agregar_cliente.domain.usecase.AgregarClienteUseCase
import com.draken.app_movil_pm.features.editar_cliente.domain.usecase.EditarClienteUseCase

class EditarClienteViewModelFactory(
    private val useCase: EditarClienteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditarClienteViewModel(useCase) as T
    }
}