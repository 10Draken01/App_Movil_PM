package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val BORDER_WIDTH = 1.dp

@Composable
fun ImageContainer(
    imageSource: Any?,
    hasImage: Boolean,
    isPreview: Boolean,
    backgroundColor: Color,
    borderColor: Color,
    loadingColor: Color,
    size: androidx.compose.ui.unit.Dp
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .border(
                width = BORDER_WIDTH,
                color = borderColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (hasImage && !isPreview) {
            // Mostrar imagen con estados de loading
            ImageWithLoadingStates(
                imageSource = imageSource,
                loadingColor = loadingColor,
                size = size
            )
        } else {
            // Placeholder cuando no hay imagen
            ImagePlaceholder()
        }
    }
}