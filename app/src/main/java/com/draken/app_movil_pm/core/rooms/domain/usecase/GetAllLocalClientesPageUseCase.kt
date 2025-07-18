package com.draken.app_movil_pm.core.rooms.domain.usecase

import android.util.Log
import com.draken.app_movil_pm.core.rooms.domain.model.ResponseRooms
import com.draken.app_movil_pm.core.rooms.domain.repository.ClienteDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.catch

class GetAllLocalClientesPageUseCase(
    private val reposiClienteDBRepository: ClienteDBRepository
) {
    // La función invoke ahora devuelve un Flow<ResponseRooms>
    suspend operator fun invoke(): Flow<ResponseRooms> {

        return reposiClienteDBRepository.getAllClientes()
            .map { clientesList ->
                if (clientesList.isNotEmpty()) {
                    ResponseRooms(
                        data = clientesList,
                        message = "Clientes obtenidos correctamente",
                        isLoading = false
                    )
                } else {
                    ResponseRooms(
                        error = "No se encontraron clientes",
                        isLoading = false
                    )
                }
            }
            .catch { exception ->
                Log.e("ErrorGetClientesPage", "Error en el flow: ${exception.message}", exception)
                emit(ResponseRooms(
                    error = "Error al obtener clientes: ${exception.message}",
                    isLoading = false
                ))
            }
    }
}