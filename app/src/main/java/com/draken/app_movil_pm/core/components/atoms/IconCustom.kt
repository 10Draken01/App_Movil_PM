package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconCustom(
    iconRes: Int,
    size: Dp,
    modifier: Modifier = Modifier,
    tint: Color = Color.Red,
    backgroundColor: Color = Color.Unspecified,
    contentDescription: String? = null,
    padding: Dp = 8.dp
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = CircleShape,
            color = backgroundColor,
            modifier = Modifier.size(size + padding * 2) // Surface más grande que el icon
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = contentDescription,
                    tint = tint,
                    modifier = Modifier.size(size) // Tamaño aplicado al Icon
                )
            }
        }
    }
}