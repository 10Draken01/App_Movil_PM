package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Arrangement
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
private val SPACING_MEDIUM = 12.dp

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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SPACING_MEDIUM)
    ) {
        // Avatar del cliente
        CharacterIcon(
            characterIcon = cliente.characterIcon,
            size = AVATAR_SIZE
        )

        // Información principal del cliente
        ClienteMainInfo(
            cliente = cliente,
            modifier = Modifier.weight(1f)
        )

        // Sección de acciones y estado
        ClienteActionsSection(
            cliente = cliente,
            isExpanded = isExpanded,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick
        )
    }
}