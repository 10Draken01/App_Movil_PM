package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.ButtonIconCustom
import com.draken.app_movil_pm.R

// Constantes para mantener consistencia en el diseño
private val ICON_SIZE_MEDIUM = 22.dp
private val SPACING_SMALL = 4.dp
@Composable
fun ActionButtons(
    clienteNombre: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL)
    ) {
        ButtonIconCustom(
            icon = painterResource(id = R.drawable.boton_editar),
            contentDescription = "Editar Cliente $clienteNombre",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            sizeIcon = ICON_SIZE_MEDIUM,
            onClick = onEditClick
        )

        ButtonIconCustom(
            icon = painterResource(id = R.drawable.borrar),
            contentDescription = "Eliminar Cliente $clienteNombre",
            color = MaterialTheme.colorScheme.error,
            sizeIcon = ICON_SIZE_MEDIUM,
            onClick = onDeleteClick
        )
    }
}
