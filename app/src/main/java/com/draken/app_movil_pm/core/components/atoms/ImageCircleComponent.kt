package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import com.draken.app_movil_pm.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun ImageCircleComponent(
    imageUri: Uri? = null,
    imageUrl: String? = null,
    onDeleteClick: () -> Unit = {},
    justPreview: Boolean = false
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    var imageLoadError by remember { mutableStateOf(false) }

    // Priorizar imageUrl sobre imageUri
    val imageSource = when {
        !imageUrl.isNullOrBlank() -> imageUrl
        imageUri != null -> imageUri
        else -> null
    }

    val hasImage = imageSource != null

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(120.dp)
                .background(Color(0, 0, 0, alpha = 0)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = if (hasImage && !imageLoadError) Color.Green.copy(alpha = 0.7f) else Color.Black.copy(alpha = 0.3f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (hasImage && !imageLoadError && !isPreview) {
                    // Mostrar imagen cargada con mejor configuración
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageSource)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        onError = {
                            imageLoadError = true
                        },
                        onSuccess = {
                            imageLoadError = false
                        }
                    )
                } else {
                    // Placeholder mejorado
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
                                modifier = Modifier.size(40.dp),
                                tint = Color.Unspecified
                            )

                            if (imageLoadError) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Error al cargar",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Red.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }

            // Botón de borrar mejorado - solo mostrar si hay imagen
            if (hasImage && !justPreview) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 18.dp)
                        .size(40.dp)
                        .background(
                            color = Color(0xFFFFC5C5),
                            shape = CircleShape
                        )
                        .border(
                            width = 2.dp,
                            color = Color.Red,
                            shape = CircleShape
                        )
                        .clickable {
                            imageLoadError = false // Reset error state
                            onDeleteClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.borrar_red),
                        contentDescription = "Borrar imagen",
                        modifier = Modifier.size(25.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(15.dp))
}