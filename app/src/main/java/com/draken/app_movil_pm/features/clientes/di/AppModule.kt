package com.draken.app_movil_pm.features.clientes.di

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.clientes.data.datasource.remote.ClienteService
import com.draken.app_movil_pm.features.clientes.data.repository.ClienteRepositoryImpl
import com.draken.app_movil_pm.features.clientes.domain.repository.ClienteRepository
import com.draken.app_movil_pm.features.clientes.domain.usecase.GetClientesUseCase

object AppModule {
    private val api: ClienteService = RetrofitHelper.retrofitInstance.create(ClienteService::class.java)

    private val repository: ClienteRepository = ClienteRepositoryImpl(api)
    val getClientesUseCase = GetClientesUseCase(repository)
}