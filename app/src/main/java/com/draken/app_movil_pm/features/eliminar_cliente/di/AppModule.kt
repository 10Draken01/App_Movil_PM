package com.draken.app_movil_pm.features.eliminar_cliente.di

import com.draken.app_movil_pm.core.http.RetrofitHelper

import com.draken.app_movil_pm.features.eliminar_cliente.data.datasource.remote.EliminarClienteService
import com.draken.app_movil_pm.features.eliminar_cliente.data.repository.EliminarClienteRepositoryImpl
import com.draken.app_movil_pm.features.eliminar_cliente.domain.repository.EliminarClienteRepository
import com.draken.app_movil_pm.features.eliminar_cliente.domain.usecase.EliminarClienteUseCase

object AppModule {
    private val api: EliminarClienteService = RetrofitHelper.retrofitInstance.create(
        EliminarClienteService::class.java)

    private val repository: EliminarClienteRepository = EliminarClienteRepositoryImpl(api)
    val eliminarClienteUseCase = EliminarClienteUseCase (repository)
}