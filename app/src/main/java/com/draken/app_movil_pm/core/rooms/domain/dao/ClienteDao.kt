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
    // Agregar un cliente
    // Retornar el cliente agregado
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCliente(cliente: ClienteEntitie): Long

    // Consulta para obtener todos los clientes por pagina
    // 100 clientes por pagina
    // la pagina minima es 1 y la maxima es las que tenga la db
    // La page se obtiene calculando los documentos totales / 100 y redondeando para arriba
    // Y ademas sin no se pueden traer 100 clientes que traigan los que halla
    @Query("SELECT * FROM Clientes LIMIT :limit OFFSET :offset")
    fun getClientesPage(limit: Int, offset: Int): Flow<List<ClienteEntitie>>

    // Consulta para actualizar un cliente por el claveCliente
    // No actualiza claveCliente
    // Si el campo es nulo no se actualiza
    // Si el campo no existe no se actualiza
    // en characterIcon los campos nulos si se actualizan y borran esa columna
    // en characterIconUrl las columnas inexistentes se crean y se actualizan
    // retornar la cantidad de clientes actualizados
    @Query("UPDATE Clientes SET nombre = :nombre, celular = :celular, email = :email, characterIcon = :characterIcon WHERE claveCliente = :claveCliente")
    suspend fun updateCliente(claveCliente: String, nombre: String, celular: String, email: String, characterIcon: CharacterIcon): Int

    // Consulta para obtener un cliente por claveCliente
    @Query("SELECT * FROM Clientes WHERE claveCliente = :claveCliente")
    suspend fun getClienteByClaveCliente(claveCliente: String): ClienteEntitie?

    // Consulta para borrar por claveCliente
    // retorna la cantidad de clientes borrados
    @Query("DELETE FROM Clientes WHERE claveCliente = :claveCliente")
    suspend fun deleteClienteByClaveCliente(claveCliente: String): Int

    // Consulta para obtener la cantidad de clientes
    @Query("SELECT COUNT(*) FROM Clientes")
    suspend fun getClienteCount(): Int
}
