package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms

import androidx.compose.foundation.background
import com.draken.app_movil_pm.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ButtonArrow(
    onClick: () -> Unit,
    rotation: Float? = null,
) {
    Surface(
        shape = CircleShape,
        color = Color.Unspecified,
        modifier = Modifier.size(50.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "ArrowIcon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(50.dp) // ✅ Tamaño específico igual al Surface
                    .rotate(rotation ?: 0f)
            )
        }
    }
}