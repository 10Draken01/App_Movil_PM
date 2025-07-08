package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.ButtonIconCustom
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.ClienteInfoRow
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold


// Constantes para mantener consistencia en el diseño
private val CONTENT_PADDING = 12.dp
private val AVATAR_SIZE = 50.dp
private val ICON_SIZE_SMALL = 20.dp
private val ICON_SIZE_MEDIUM = 22.dp
private val SPACING_SMALL = 4.dp
private val SPACING_MEDIUM = 12.dp
private val TEXT_SIZE_TITLE = 16.sp
private val LINE_HEIGHT_TITLE = 18.sp

@Composable
fun ClienteCardHeader(
    cliente: Cliente,
    isExpanded: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CONTENT_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar del cliente
        CharacterIcon(
            characterIcon = cliente.characterIcon,
            size = AVATAR_SIZE
        )

        Spacer(modifier = Modifier.width(SPACING_MEDIUM))

        // Información principal
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = cliente.nombre,
                fontFamily = Spooftrial_bold,
                fontSize = TEXT_SIZE_TITLE,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = LINE_HEIGHT_TITLE
            )

            Spacer(modifier = Modifier.height(SPACING_SMALL))

            ClienteInfoRow(
                label = "Clave:",
                value = cliente.claveCliente
            )
        }

        Spacer(modifier = Modifier.width(SPACING_MEDIUM))

        // Indicador de expansión
        Icon(
            imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = if (isExpanded) "Contraer" else "Expandir",
            tint = Color.Black,
            modifier = Modifier.size(ICON_SIZE_SMALL)
        )

        Spacer(modifier = Modifier.width(SPACING_SMALL))

        // Botones de acción
        ButtonIconCustom(
            icon = painterResource(id = R.drawable.boton_editar),
            contentDescription = "Editar Cliente ${cliente.nombre}",
            color = Color.DarkGray,
            sizeIcon = ICON_SIZE_MEDIUM,
            onClick = onEditClick
        )

        Spacer(modifier = Modifier.width(SPACING_SMALL))

        ButtonIconCustom(
            icon = painterResource(id = R.drawable.borrar),
            contentDescription = "Eliminar Cliente ${cliente.nombre}",
            color = Color.Black,
            sizeIcon = ICON_SIZE_MEDIUM,
            onClick = onDeleteClick
        )
    }
}