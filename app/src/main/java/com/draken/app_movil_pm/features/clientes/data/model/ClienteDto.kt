package com.draken.app_movil_pm.features.clientes.data.model

import android.util.Log
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.CharacterIconUrl
import com.draken.app_movil_pm.core.domain.model.Cliente

data class ClienteDto(
    val id: String,
    val claveCliente: String,
    val nombre: String,
    val celular: String,
    val email: String,
    val characterIcon: Any
) {
    fun toDomain(): Cliente {
        val characterIconDomain = when (// Caso 1: Es un número (Int o Double)
            characterIcon) {
            is Number -> {
                CharacterIcon(characterIconNumber = characterIcon.toInt())
            }

            // Caso 2: Es un Map (objeto JSON deserializado)
            is Map<*, *> -> {
                try {
                    val id = characterIcon["id"]?.toString() ?: ""
                    val url = characterIcon["url"]?.toString() ?: ""

                    if (id.isNotEmpty() && url.isNotEmpty()) {
                        CharacterIcon(
                            characterIconUrl = CharacterIconUrl(
                                id = id,
                                url = url
                            )
                        )
                    } else {
                        // Si no tiene id o url válidos, usar valor por defecto
                        CharacterIcon(characterIconNumber = 1)
                    }
                } catch (e: Exception) {
                    // En caso de error, usar valor por defecto
                    Log.e("ClienteDto", "Error: ${e.message}")
                    CharacterIcon(characterIconNumber = 1)

                }
            }

            // Caso por defecto para cualquier otro tipo
            else -> {
                CharacterIcon(characterIconNumber = 1)
            }
        }

        return Cliente(
            claveCliente = claveCliente,
            nombre = nombre,
            celular = celular,
            email = email,
            characterIcon = characterIconDomain
        )
    }
}
