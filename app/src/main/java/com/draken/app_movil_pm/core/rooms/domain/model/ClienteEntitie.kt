package com.draken.app_movil_pm.core.rooms.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.domain.model.QueryType
import java.util.Date

@Entity(tableName = "clientes")
data class ClienteEntitie(
    @PrimaryKey // ← LÍNEA AGREGADA (CRÍTICA)
    @ColumnInfo(name = "claveCliente")
    val claveCliente: String,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "celular")
    val celular: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "characterIcon")
    val characterIcon: CharacterIcon,

    @ColumnInfo(name = "createdAt")
    val createdAt: Date? = null,

    @ColumnInfo(name = "updatedAt")
    val updatedAt: Date? = null,

    @ColumnInfo(name = "querytype")
    val queryType: QueryType = QueryType.NO_SPECIFIED
) {
    fun toDomain() = Cliente(
        claveCliente = claveCliente,
        nombre = nombre,
        celular = celular,
        email = email,
        characterIcon = characterIcon,
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString(),
        queryType = queryType,
        isLocal = true
    )
}