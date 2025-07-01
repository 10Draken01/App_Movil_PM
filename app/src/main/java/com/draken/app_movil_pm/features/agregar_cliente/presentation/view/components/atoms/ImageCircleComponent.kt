package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.R

@Composable
fun ImageCircleComponent(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {},
    circleSize: Int = 120,
    hasImage: Boolean = false,
    imageContent: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = modifier.size(circleSize.dp),
        contentAlignment = Alignment.Center
    ) {
        // Círculo principal con borde
        Box(
            modifier = Modifier
                .size(circleSize.dp)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (hasImage && imageContent != null) {
                // Contenido de imagen personalizado
                imageContent()
            } else {
                // Ícono placeholder por defecto
                Icon(
                    painter = painterResource(id = R.drawable.imagen_icono),
                    contentDescription = "Imagen placeholder",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray
                )
            }
        }

        // Botón de eliminar en la esquina inferior derecha
        FloatingActionButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(36.dp),
            containerColor = Color.Red,
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.borrar_red),
                contentDescription = "Eliminar",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageCircleComponentPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Versión básica
        ImageCircleComponent(
            onDeleteClick = { /* Acción de eliminar */ }
        )

        // Versión con contenido personalizado
        ImageCircleComponent(
            hasImage = true,
            onDeleteClick = { /* Acción de eliminar */ }
        ) {
            // Aquí podrías poner una imagen real con AsyncImage de Coil
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("IMG", color = Color.DarkGray)
            }
        }
    }
}