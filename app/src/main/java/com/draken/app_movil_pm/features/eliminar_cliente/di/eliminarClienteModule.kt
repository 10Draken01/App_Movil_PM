package com.draken.app_movil_pm.features.eliminar_cliente.di

import com.draken.app_movil_pm.core.http.RetrofitHelper

import com.draken.app_movil_pm.features.eliminar_cliente.data.datasource.remote.EliminarClienteService
import com.draken.app_movil_pm.features.eliminar_cliente.data.repository.EliminarClienteRepositoryImpl
import com.draken.app_movil_pm.features.eliminar_cliente.domain.repository.EliminarClienteRepository
import com.draken.app_movil_pm.features.eliminar_cliente.domain.usecase.EliminarClienteUseCase

object eliminarClienteModule {

    init {
        RetrofitHelper.init()
    }

    private val eliminarClienteService: EliminarClienteService by lazy {
        RetrofitHelper.getService(EliminarClienteService::class.java)
    }

    private val eliminarClienteRepository: EliminarClienteRepository by lazy {
        EliminarClienteRepositoryImpl(eliminarClienteService)
    }

    val eliminarClienteUseCase: EliminarClienteUseCase by lazy {
        EliminarClienteUseCase(eliminarClienteRepository)
    }
}