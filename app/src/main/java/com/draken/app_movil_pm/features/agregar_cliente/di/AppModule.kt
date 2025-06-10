package com.draken.app_movil_pm.features.agregar_cliente.di

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.agregar_cliente.data.datasource.remote.AgregarClienteService
import com.draken.app_movil_pm.features.agregar_cliente.data.repository.AgregarClienteRepositoryImpl
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository
import com.draken.app_movil_pm.features.agregar_cliente.domain.usecase.AgregarClienteUseCase

object AppModule {
    private val api: AgregarClienteService = RetrofitHelper.retrofitInstance.create(AgregarClienteService::class.java)

    private val repository: AgregarClienteRepository = AgregarClienteRepositoryImpl(api)
    val agregarClienteUseCase = AgregarClienteUseCase (repository)
}