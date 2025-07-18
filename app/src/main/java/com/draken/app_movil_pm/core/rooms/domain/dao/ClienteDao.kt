package com.draken.app_movil_pm.core.rooms.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    // Agregar un cliente - Retorna el ID del cliente insertado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: ClienteEntitie): Long

    // Obtener clientes por página (sin suspend porque retorna Flow)
    @Query("SELECT * FROM clientes LIMIT :limit OFFSET :offset")
    fun getClientesPage(limit: Int, offset: Int): Flow<List<ClienteEntitie>>

    // Obtener todos los clientes de la tabla
    @Query("SELECT * FROM clientes")
    suspend fun getAllClientes(): Flow<List<ClienteEntitie>>

    // Actualizar cliente por claveCliente - Retorna cantidad de filas afectadas
    @Query("""
        UPDATE clientes 
        SET nombre = :nombre, 
            celular = :celular, 
            email = :email, 
            characterIcon = :characterIcon 
        WHERE claveCliente = :claveCliente
    """)
    suspend fun updateCliente(
        claveCliente: String,
        nombre: String,
        celular: String,
        email: String,
        characterIcon: CharacterIcon
    ): Int

    // Obtener un cliente por claveCliente
    @Query("SELECT * FROM clientes WHERE claveCliente = :claveCliente")
    suspend fun getClienteByClaveCliente(claveCliente: String): ClienteEntitie?

    // Borrar cliente por claveCliente - Retorna cantidad de filas eliminadas
    @Query("DELETE FROM clientes WHERE claveCliente = :claveCliente")
    suspend fun deleteClienteByClaveCliente(claveCliente: String): Int

    // Obtener cantidad total de clientes
    @Query("SELECT COUNT(*) FROM clientes")
    suspend fun getClienteCount(): Int
}