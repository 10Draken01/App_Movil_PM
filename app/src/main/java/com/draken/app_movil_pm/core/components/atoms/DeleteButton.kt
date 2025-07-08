package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
// Remove the FlowRowScopeInstance import - you don't need it!
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.R

// Constantes para mantener consistencia y escalabilidad
private val BORDER_WIDTH = 1.dp
private const val DELETE_BUTTON_SIZE_RATIO = 0.33f
private const val DELETE_ICON_SIZE_RATIO = 0.625f
private const val DELETE_BUTTON_OFFSET_RATIO = 0.15f
private val MIN_DELETE_BUTTON_SIZE = 32.dp
private val DELETE_BUTTON_BACKGROUND = Color(0xFFFFC5C5)
private val DELETE_BUTTON_BORDER = Color.Red

@Composable
fun DeleteButton(
    onDeleteClick: () -> Unit,
    size: androidx.compose.ui.unit.Dp
) {
    val deleteButtonSize = (size * DELETE_BUTTON_SIZE_RATIO).coerceAtLeast(MIN_DELETE_BUTTON_SIZE)
    val deleteIconSize = (deleteButtonSize * DELETE_ICON_SIZE_RATIO)
    val offsetY = (size * DELETE_BUTTON_OFFSET_RATIO)

    Box(
        modifier = Modifier
            .offset(y = (size - 5.dp) / 2)
            .size(deleteButtonSize)
            .background(
                color = DELETE_BUTTON_BACKGROUND,
                shape = CircleShape
            )
            .border(
                width = BORDER_WIDTH,
                color = DELETE_BUTTON_BORDER,
                shape = CircleShape
            )
            .clickable { onDeleteClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.borrar_red),
            contentDescription = "Borrar imagen",
            modifier = Modifier.size(deleteIconSize),
            tint = Color.Unspecified
        )
    }
}