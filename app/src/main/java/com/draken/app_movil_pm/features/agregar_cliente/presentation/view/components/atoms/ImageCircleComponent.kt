package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.draken.app_movil_pm.R
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import android.net.Uri
import androidx.compose.foundation.clickable

@Composable
fun ImageCircleComponent(
    imageUri: Uri? = null,
    onDeleteClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier.size(120.dp)
                .background(Color(0,0,0, alpha = 0)),
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
                        width = 1.dp,
                        color = Color.Black.copy(alpha = 0.8f), // Borde más suave
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    // Mostrar imagen cargada
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Placeholder de imagen mejorado
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = Color.LightGray.copy(alpha = 0f), // Más sutil
                                shape = RoundedCornerShape(8.dp)
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
                        }
                    }
                }
            }


            // Botón de borrar en el borde del círculo
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (18).dp) // Posicionado en el borde
                    .size(40.dp) // Círculo más pequeño
                    .background(
                        color = Color(0xFFFFC5C5), // Color rosa claro
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .clickable { onDeleteClick() }, // Clickeable directamente en el Box
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.borrar_red),
                    contentDescription = "Borrar imagen",
                    modifier = Modifier.size(25.dp), // Icono proporcionalmente ajustado
                    tint = Color.Unspecified
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(15.dp))
}