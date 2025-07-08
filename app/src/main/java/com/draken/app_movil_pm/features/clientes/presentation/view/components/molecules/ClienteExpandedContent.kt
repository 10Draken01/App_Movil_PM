package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.ClienteDetailRow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

// Constantes para mantener consistencia en el diseño
private val CONTENT_PADDING = 12.dp
private val AVATAR_SIZE = 50.dp
private val SPACING_MEDIUM = 5.dp
@Composable
fun ClienteExpandedContent(cliente: Cliente) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = (CONTENT_PADDING + AVATAR_SIZE + SPACING_MEDIUM),
                end = CONTENT_PADDING,
                bottom = CONTENT_PADDING
            ),
        verticalArrangement = Arrangement.spacedBy(SPACING_MEDIUM)
    ) {
        ClienteDetailRow(
            icon = Icons.Filled.Phone,
            label = "Teléfono",
            value = cliente.celular
        )

        ClienteDetailRow(
            icon = Icons.Filled.Email,
            label = "Email",
            value = cliente.email
        )

        ClienteDetailRow(
            icon = Icons.Filled.DateRange,
            label = "Fecha de Creación",
            value = formatDate(cliente.createdAt)
        )

        ClienteDetailRow(
            icon = Icons.Filled.Done,
            label = "Última Actualización",
            value = formatDate(cliente.updatedAt)
        )
    }
}

private fun formatDate(dateString: String?): String {
    return try {
        if (dateString.isNullOrEmpty()) return "N/A"

        // Parser para formato ISO 8601
        val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        // Formatter para mostrar la fecha
        val displayFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

        val date = isoFormatter.parse(dateString)
        date?.let { displayFormatter.format(it) } ?: "N/A"
    } catch (e: Exception) {
        dateString ?: "N/A"
    }
}