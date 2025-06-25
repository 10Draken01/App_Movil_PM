package com.draken.app_movil_pm.features.editar_cliente.di

import com.draken.app_movil_pm.core.http.RetrofitHelper

import com.draken.app_movil_pm.features.editar_cliente.data.datasource.remote.EditarClienteService
import com.draken.app_movil_pm.features.editar_cliente.data.repository.EditarClienteRepositoryImpl
import com.draken.app_movil_pm.features.editar_cliente.domain.repository.EditarClienteRepository
import com.draken.app_movil_pm.features.editar_cliente.domain.usecase.EditarClienteUseCase

object AppModule {

    private val editarClienteService: EditarClienteService by lazy {
        RetrofitHelper.getService(EditarClienteService::class.java)
    }

    private val repositoryEditarCliente: EditarClienteRepository by lazy {
        EditarClienteRepositoryImpl(editarClienteService)
    }

    val editarClienteUseCase: EditarClienteUseCase by lazy {
        EditarClienteUseCase(repositoryEditarCliente)
    }
}