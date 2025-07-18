package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.ButtonIconCustom
import com.draken.app_movil_pm.R

// Constantes para mantener consistencia en el diseño
private val ICON_SIZE_MEDIUM = 15.dp
private val SPACING_SMALL = 4.dp
@Composable
fun ActionButtons(
    clienteNombre: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier // ← Agregar parámetro modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL)
    ) {
        ButtonIconCustom(
            icon = painterResource(id = R.drawable.boton_editar),
            contentDescription = "Editar Cliente $clienteNombre",
            color = Color.DarkGray,
            sizeIcon = ICON_SIZE_MEDIUM,
            onClick = onEditClick
        )

        ButtonIconCustom(
            icon = painterResource(id = R.drawable.borrar),
            contentDescription = "Eliminar Cliente $clienteNombre",
            color = Color.Black,
            sizeIcon = ICON_SIZE_MEDIUM,
            onClick = onDeleteClick
        )
    }
}
