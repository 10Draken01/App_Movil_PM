package com.draken.app_movil_pm.core.rooms.domain.usecase

import android.util.Log
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie
import com.draken.app_movil_pm.core.rooms.domain.repository.ClienteDBRepository

class AddClienteUseCase(
    private val reposiClienteDBRepository: ClienteDBRepository
) {
    suspend operator fun invoke(cliente: Cliente): Response {
        try {
            val clienteEntitie = ClienteEntitie(
                claveCliente = cliente.claveCliente,
                nombre = cliente.nombre,
                celular = cliente.celular,
                email = cliente.email,
                characterIcon = cliente.characterIcon,
            )

            val ok = reposiClienteDBRepository.insertCliente(clienteEntitie)

            return if( ok ) {
                Response(
                    message = "Cliente agregado"
                )
            } else {
                Response(
                    error = "Error al agregar cliente"
                )

            }
        } catch (error: Error) {
            Log.e("ErrrorAddClienteDB", "Error: ${error.message}")
            return Response(
                error = "Error al agregar cliente: ${error.message}"
            )
        }
    }
}