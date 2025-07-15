package com.draken.app_movil_pm.core.rooms.domain.usecase

import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie

class DeleteClienteUseCase(
    private val repositoryFunDeleteCliente: suspend (claveCliente: String) -> Boolean
) {
    suspend operator fun invoke(claveCliente: String): Response {

        val ok = repositoryFunDeleteCliente(claveCliente)
        return if( ok ) {
            Response(
                message = "Cliente eliminado"
            )
        } else {
            Response(
                error = "Error al eliminar cliente"
            )
        }
    }
}