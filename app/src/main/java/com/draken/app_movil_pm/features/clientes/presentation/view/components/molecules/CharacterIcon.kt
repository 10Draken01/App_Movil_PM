package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.LocalIcon

// Constantes para mantener consistencia
private val DEFAULT_ICON_SIZE = 50.dp
private const val ICON_SIZE_RATIO = 0.6f

@Composable
fun CharacterIcon(
    characterIcon: CharacterIcon,
    modifier: Modifier = Modifier,
    size: Dp = DEFAULT_ICON_SIZE,
    backgroundColor: Color = Color.Black,
    loadingColor: Color = Color.White,
    iconSize: Dp = size * ICON_SIZE_RATIO
) {
    Surface(
        shape = CircleShape,
        color = backgroundColor,
        modifier = modifier.size(size)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Priorizar URL si existe y no está vacía
            if (!characterIcon.characterIconUrl?.url.isNullOrEmpty()) {
                ImageWithLoading(
                    imageUrl = characterIcon.characterIconUrl.url,
                    loadingColor = loadingColor,
                    fallbackIconRes = characterIcon.characterIconNumber,
                    iconSize = iconSize
                )
            } else {
                // Usar icono local si no hay URL
                LocalIcon(
                    iconRes = characterIcon.characterIconNumber,
                    iconSize = iconSize
                )
            }
        }
    }
}