package com.draken.app_movil_pm.core.rooms.domain.model

data class ResponseRooms(
    val data: List<ClienteEntitie>? = null,
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
}