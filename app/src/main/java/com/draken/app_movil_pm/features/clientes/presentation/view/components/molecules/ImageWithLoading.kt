package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.draken.app_movil_pm.core.components.atoms.LoadingIndicator
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.LocalIcon

@Composable
fun ImageWithLoading(
    imageUrl: String,
    loadingColor: Color,
    fallbackIconRes: Int,
    iconSize: Dp
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "Profile Image",
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            is coil.compose.AsyncImagePainter.State.Loading -> {
                LoadingIndicator(color = loadingColor)
            }
            is coil.compose.AsyncImagePainter.State.Error -> {
                // En caso de error, mostrar el icono local como fallback
                LocalIcon(
                    iconRes = fallbackIconRes,
                    iconSize = iconSize
                )
            }
            else -> {
                // Imagen cargada exitosamente
                SubcomposeAsyncImageContent()
            }
        }
    }
}