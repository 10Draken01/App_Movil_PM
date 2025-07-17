package com.draken.app_movil_pm.features.clientes.di

import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.clientes.data.datasource.remote.ClienteService
import com.draken.app_movil_pm.features.clientes.data.repository.ClienteRepositoryImpl
import com.draken.app_movil_pm.features.clientes.domain.repository.ClienteRepository
import com.draken.app_movil_pm.features.clientes.domain.usecase.GetClientesPageUseCase

object ClientesModule {

    init {
        RetrofitHelper.init()
    }

    private val clienteService: ClienteService by lazy {
        RetrofitHelper.getService(ClienteService::class.java)
    }

    private val clienteRepository: ClienteRepository by lazy {
        ClienteRepositoryImpl(clienteService)
    }

    val getClientesPageUseCase: GetClientesPageUseCase by lazy {
        GetClientesPageUseCase(clienteRepository)
    }
}