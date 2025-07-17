package com.draken.app_movil_pm.core.di

import com.draken.app_movil_pm.core.app_context.AppContextHolder
import com.draken.app_movil_pm.core.rooms.data.repository.ClienteDBRepositoryImpl
import com.draken.app_movil_pm.core.rooms.data.service.ClientesAppDatabase
import com.draken.app_movil_pm.core.rooms.domain.repository.ClienteDBRepository
import com.draken.app_movil_pm.core.rooms.domain.usecase.AddLocalClienteUseCase
import com.draken.app_movil_pm.core.rooms.domain.usecase.DeleteLocalClienteUseCase
import com.draken.app_movil_pm.core.rooms.domain.usecase.GetLocalClientesPageUseCase
import com.draken.app_movil_pm.core.rooms.domain.usecase.UpdateLocalClienteUseCase

object LocalClientesModule {
    val clientesAppDatabase = ClientesAppDatabase.getDatabase(
        context =AppContextHolder.get()
    )

    val clienteDBRepository: ClienteDBRepository by lazy {
        ClienteDBRepositoryImpl(
            database = clientesAppDatabase
        )
    }

    val getLocalClientesPageUseCase = GetLocalClientesPageUseCase(
        reposiClienteDBRepository = clienteDBRepository
    )

    val addLocalClienteUseCase = AddLocalClienteUseCase(
        reposiClienteDBRepository = clienteDBRepository
    )

    val deleteLocalClienteUseCase = DeleteLocalClienteUseCase(
        reposiClienteDBRepository = clienteDBRepository
    )

    val updateLocalClienteUseCase = UpdateLocalClienteUseCase(
        reposiClienteDBRepository = clienteDBRepository
    )
}