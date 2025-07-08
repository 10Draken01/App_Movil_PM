package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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

private val PLACEHOLDER_ICON_SIZE = 40.dp
@Composable
fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.LightGray.copy(alpha = 0.1f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.imagen_icono),
                contentDescription = "Seleccionar imagen",
                modifier = Modifier.size(PLACEHOLDER_ICON_SIZE),
                tint = Color.Unspecified
            )
        }
    }
}