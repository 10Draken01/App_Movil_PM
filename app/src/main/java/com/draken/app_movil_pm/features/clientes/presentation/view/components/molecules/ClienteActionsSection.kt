package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(SPACING_SMALL)
    ) {
        // Botones de acción y indicador de expansión
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL)
        ) {
            // Botones de acción
            ActionButtons(
                clienteNombre = cliente.nombre,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )

            // Indicador de expansión
            ExpandIndicator(isExpanded = isExpanded)
        }

        // Label de estado (Local/Nube)
        StatusLabel(isLocal = cliente.isLocal)
    }
}
