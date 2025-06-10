package com.draken.app_movil_pm.features.eliminar_cliente.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draken.app_movil_pm.features.editar_cliente.domain.usecase.EditarClienteUseCase
import com.draken.app_movil_pm.features.eliminar_cliente.domain.usecase.EliminarClienteUseCase

class EliminarClienteViewModelFactory(
    private val useCase: EliminarClienteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EliminarClienteViewModel(useCase) as T
    }
}