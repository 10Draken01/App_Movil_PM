package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.StatusLabel

// Constantes para mantener consistencia en el diseño
private val SPACING_SMALL = 4.dp

@Composable
fun ClienteActionsSection(
    cliente: Cliente,
    isExpanded: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
    ) {
        // Indicador de expansión
        ExpandIndicator(isExpanded = isExpanded)

        // Column que contiene los botones arriba y el StatusLabel abajo
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING_SMALL),
            modifier = Modifier
                .width(IntrinsicSize.Max)
        ) {
            // Botones de acción (arriba)
            ActionButtons(
                clienteNombre = cliente.nombre,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )

            // Label de estado (abajo) - ocupa el ancho máximo del contenedor
            StatusLabel(
                isLocal = cliente.isLocal,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}