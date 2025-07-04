package com.draken.app_movil_pm.features.clientes.data.repository

import com.draken.app_movil_pm.features.clientes.data.datasource.remote.ClienteService
import com.draken.app_movil_pm.features.clientes.data.model.ClienteResponseDto
import com.draken.app_movil_pm.features.clientes.data.model.DataResponse
import com.draken.app_movil_pm.features.clientes.domain.repository.ClienteRepository

class ClienteRepositoryImpl(private val api: ClienteService) : ClienteRepository {
    override suspend fun getClientes(page: Int): Result<ClienteResponseDto> {
        return try {
            val response = api.getClientes(page)


            if (response.isSuccessful) {
                Result.success(ClienteResponseDto(
                    success = response.body()!!.success,
                    data = response.body()!!.data)
                )
            } else {
                Result.success(ClienteResponseDto(
                    success = response.body()!!.success,
                    data = DataResponse(clientes = emptyList())
                ))

            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
