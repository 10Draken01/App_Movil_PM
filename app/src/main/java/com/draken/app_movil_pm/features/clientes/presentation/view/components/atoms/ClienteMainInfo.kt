package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold

// Constantes para mantener consistencia en el diseño
private val SPACING_SMALL = 4.dp
private val TEXT_SIZE_TITLE = 16.sp
private val LINE_HEIGHT_TITLE = 18.sp
@Composable
private fun ClienteMainInfo(
    cliente: Cliente,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SPACING_SMALL)
    ) {
        // Nombre del cliente
        Text(
            text = cliente.nombre,
            fontFamily = Spooftrial_bold,
            fontSize = TEXT_SIZE_TITLE,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = LINE_HEIGHT_TITLE
        )

        // Clave del cliente
        ClienteInfoRow(
            label = "Clave:",
            value = cliente.claveCliente
        )
    }
}