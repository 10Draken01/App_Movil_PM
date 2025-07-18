package com.draken.app_movil_pm.core.rooms.data.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.draken.app_movil_pm.core.rooms.data.domain.Converters
import com.draken.app_movil_pm.core.rooms.domain.dao.ClienteDao
import com.draken.app_movil_pm.core.rooms.domain.model.ClienteEntitie // Assuming this path is correct


@Database(
    entities = [ClienteEntitie::class],
    version = 1,
    exportSchema = false // Consider setting to true for production builds for schema history
)
@TypeConverters(Converters::class)
abstract class ClientesAppDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao // Esto ya debería estar


    companion object {
        @Volatile
        private var INSTANCE: ClientesAppDatabase? = null

        fun getDatabase(context: Context): ClientesAppDatabase {
            // If the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext?: context,
                    klass = ClientesAppDatabase::class.java,
                    name = "app_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not covered in this lesson.
                    .fallbackToDestructiveMigration() // Use with caution, especially in production
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}