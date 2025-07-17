package com.draken.app_movil_pm.core.rooms.domain.model

import com.draken.app_movil_pm.core.domain.model.Cliente

data class ResponseRooms(
    val data: List<ClienteEntitie> = emptyList(),
    val error: String? = null,
    val message: String? = null,
    val isLoading: Boolean = false
) {
    val isSuccess: Boolean
        get() = error == null && data != null

    val isEmpty: Boolean
        get() = data?.isEmpty() ?: true

    val hasError: Boolean
        get() = error != null

    fun toDomain(): List<Cliente> {
        return data.map { it.toDomain() }
    }
}