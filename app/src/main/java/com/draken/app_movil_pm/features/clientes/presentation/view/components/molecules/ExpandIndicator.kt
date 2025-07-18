package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val ICON_SIZE_SMALL = 20.dp
@Composable
fun ExpandIndicator(isExpanded: Boolean) {
    Icon(
        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
        contentDescription = if (isExpanded) "Contraer información" else "Expandir información",
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.size(ICON_SIZE_SMALL)
    )
}