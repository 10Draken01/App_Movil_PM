package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest

@Composable
fun ImageWithLoadingStates(
    imageSource: Any?,
    loadingColor: Color,
    size: androidx.compose.ui.unit.Dp
) {
    val context = LocalContext.current

    SubcomposeAsyncImage (
        model = ImageRequest.Builder(context)
            .data(imageSource)
            .crossfade(true)
            .build(),
        contentDescription = "Imagen seleccionada",
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is coil.compose.AsyncImagePainter.State.Loading -> {
                LoadingIndicatorWithBackground(
                    color = loadingColor,
                    size = size
                )
            }
            is coil.compose.AsyncImagePainter.State.Error -> {
                ErrorPlaceholder()
            }
            else -> {
                // Imagen cargada exitosamente
                SubcomposeAsyncImageContent()
            }
        }
    }
}