package com.draken.app_movil_pm.features.agregar_cliente.di

import com.draken.app_movil_pm.core.di.FileManagerModule
import com.draken.app_movil_pm.core.http.RetrofitHelper
import com.draken.app_movil_pm.features.agregar_cliente.data.datasource.remote.AgregarClienteService
import com.draken.app_movil_pm.features.agregar_cliente.data.repository.AgregarClienteRepositoryImpl
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository
import com.draken.app_movil_pm.features.agregar_cliente.domain.usecase.AgregarClienteUseCase

object AgregarClienteModule {
    init {
        RetrofitHelper.init()
    }

    private val agregarClienteService: AgregarClienteService by lazy {
        RetrofitHelper.getService(AgregarClienteService::class.java)
    }

    private val agregarClienteRepository: AgregarClienteRepository by lazy {
        AgregarClienteRepositoryImpl(agregarClienteService)
    }

    val agregarClienteUseCase: AgregarClienteUseCase by lazy {
        AgregarClienteUseCase(
            agregarClienteRepository,
            FileManagerModule.fileManagerRepository
        )
    }
}