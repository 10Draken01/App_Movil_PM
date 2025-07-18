package com.draken.app_movil_pm.core.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.core.components.atoms.DeleteButton
import com.draken.app_movil_pm.core.components.atoms.ImageContainer

private val DEFAULT_SIZE = 120.dp
private val BOTTOM_SPACING = 15.dp

@Composable
fun ImageCircleComponent(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    imageUrl: String? = null,
    onDeleteClick: () -> Unit = {},
    justPreview: Boolean = false,
    size: androidx.compose.ui.unit.Dp = DEFAULT_SIZE,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Black,
    loadingColor: Color = MaterialTheme.colorScheme.primary
) {
    val isPreview = LocalInspectionMode.current

    // Priorizar imageUrl sobre imageUri
    val imageSource = when {
        !imageUrl.isNullOrBlank() -> imageUrl
        imageUri != null -> imageUri
        else -> null
    }

    val hasImage = imageSource != null

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            // Contenedor principal de la imagen
            ImageContainer(
                imageSource = imageSource,
                hasImage = hasImage,
                isPreview = isPreview,
                backgroundColor = backgroundColor,
                borderColor = borderColor,
                loadingColor = loadingColor,
                size = size
            )

            // Botón de borrar - solo mostrar si hay imagen y no es preview
            if (hasImage && !justPreview) {
                DeleteButton(
                    onDeleteClick = onDeleteClick,
                    size = size
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(BOTTOM_SPACING))
}