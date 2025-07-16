package com.draken.app_movil_pm.core.rooms.domain.usecase

import android.util.Log
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie
import com.draken.app_movil_pm.core.rooms.domain.repository.ClienteDBRepository

class DeleteClienteUseCase(
    private val reposiClienteDBRepository: ClienteDBRepository
) {
    suspend operator fun invoke(claveCliente: String): Response {
        try {
            val ok = reposiClienteDBRepository.deleteClienteByCliente(claveCliente)
            return if( ok ) {
                Response(
                    message = "Cliente eliminado"
                )
            } else {
                Response(
                    error = "Error al eliminar cliente"
                )
            }
        } catch (error: Error) {
            // Log del error para depuración
            Log.e("DeleteClienteUseCase", "Error: ${error.message}")
            return Response(
                error = "Error al eliminar cliente: ${error.message}"
            )

        }
    }
}