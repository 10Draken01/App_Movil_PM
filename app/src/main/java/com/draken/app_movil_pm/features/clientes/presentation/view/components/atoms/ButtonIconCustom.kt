package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonIconCustom(
    icon: Painter,
    contentDescription: String,
    color: Color,
    sizeIcon: Dp,
    onClick: () -> Unit,
    rotation: Float? = null,
    enabled: Boolean = true,
    sizeButton: Dp? = null
) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = if (enabled) color else Color.LightGray,
        modifier = Modifier.size(sizeButton ?: 30.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            enabled = enabled
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(sizeIcon)
                    .rotate(rotation ?: 0f)
            )
        }
    }
}