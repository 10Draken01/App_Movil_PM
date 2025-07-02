package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular


@Composable
fun CustomToggleSwitch(
    leftText: String = "Opción 1",
    rightText: String = "Opción 2",
    isRightSelected: Boolean = false,
    onSelectionChange: (Boolean) -> Unit = {},
    textColor: Color = Color.Black
) {
    var selectedRight by remember { mutableStateOf(isRightSelected) }
    val width = 150

    val animationProgress by animateFloatAsState(
        targetValue = if (!selectedRight) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "toggle_animation"
    )

    Box(
        modifier = Modifier
            .width(width.dp)
            .height(25.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        // Rectángulo indicador con movimiento suave
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(((width) / 2).dp) // Mitad del ancho menos padding total
                .offset(x = (animationProgress * (width) / 2).dp)
                .background(
                    color = Color(0xFF000000),
                    shape = RoundedCornerShape(6.dp)
                )
        )

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Botón izquierdo
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        selectedRight = true
                        onSelectionChange(true)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = leftText,
                    color = if (selectedRight) Color.White else textColor,
                    fontSize = 14.sp,
                    fontFamily = Spooftrial_regular,
                    fontWeight = if (selectedRight) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }

            // Botón derecho
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        selectedRight = false
                        onSelectionChange(false)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rightText,
                    color = if (!selectedRight) Color.White else textColor,
                    fontSize = 14.sp,
                    fontFamily = Spooftrial_regular,
                    fontWeight = if (!selectedRight) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}