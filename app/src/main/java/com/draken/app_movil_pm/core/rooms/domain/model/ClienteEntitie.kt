package com.draken.app_movil_pm.core.rooms.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import java.util.Date

@Entity(tableName = "clientes")
data class ClienteEntitie(

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
    val updatedAt: Date? = null
)