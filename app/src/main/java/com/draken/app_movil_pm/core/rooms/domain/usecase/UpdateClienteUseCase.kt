package com.draken.app_movil_pm.core.rooms.domain.usecase

import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie

class UpdateClienteUseCase(
    private val repositoryFunUpdateCliente: suspend (cliente: ClienteEntitie) -> Boolean
) {
    suspend operator fun invoke(cliente: Cliente): Response {
        val clienteEntitie = ClienteEntitie(
            claveCliente = cliente.claveCliente,
            nombre = cliente.nombre,
            celular = cliente.celular,
            email = cliente.email,
            characterIcon = cliente.characterIcon,
        )

        val ok = repositoryFunUpdateCliente(clienteEntitie)

        return if( ok ) {
            Response(
                message = "Cliente actualizado"
            )
        } else {
            Response(
                error = "Error al actualizar cliente"
            )
        }
    }
}