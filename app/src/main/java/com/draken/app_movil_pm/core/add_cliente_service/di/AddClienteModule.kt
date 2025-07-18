package com.draken.app_movil_pm.core.add_cliente_service.di

import com.draken.app_movil_pm.core.add_cliente_service.data.datasource.remote.AddClienteService
import com.draken.app_movil_pm.core.add_cliente_service.data.repository.AddClienteRepositoryImpl
import com.draken.app_movil_pm.core.add_cliente_service.domain.repository.AddClienteRepository
import com.draken.app_movil_pm.core.add_cliente_service.domain.usecase.AddClienteUseCase
import com.draken.app_movil_pm.core.di.FileManagerModule
import com.draken.app_movil_pm.core.http.RetrofitHelper

object AddClienteModule {
    init {
        RetrofitHelper.init()
    }

    private val addClienteService: AddClienteService by lazy {
        RetrofitHelper.getService(AddClienteService::class.java)
    }

    private val addClienteRepository: AddClienteRepository by lazy {
        AddClienteRepositoryImpl(addClienteService)
    }

    val addClienteUseCase: AddClienteUseCase by lazy {
        AddClienteUseCase(
            addClienteRepository,
            FileManagerModule.fileManagerRepository
        )
    }
}