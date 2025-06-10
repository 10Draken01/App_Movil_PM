package com.draken.app_movil_pm.features.editar_cliente.di

import com.draken.app_movil_pm.core.http.RetrofitHelper

import com.draken.app_movil_pm.features.editar_cliente.data.datasource.remote.EditarClienteService
import com.draken.app_movil_pm.features.editar_cliente.data.repository.EditarClienteRepositoryImpl
import com.draken.app_movil_pm.features.editar_cliente.domain.repository.EditarClienteRepository
import com.draken.app_movil_pm.features.editar_cliente.domain.usecase.EditarClienteUseCase

object AppModule {
    private val api: EditarClienteService = RetrofitHelper.retrofitInstance.create(
        EditarClienteService::class.java)

    private val repository: EditarClienteRepository = EditarClienteRepositoryImpl(api)
    val editarClienteUseCase = EditarClienteUseCase (repository)
}