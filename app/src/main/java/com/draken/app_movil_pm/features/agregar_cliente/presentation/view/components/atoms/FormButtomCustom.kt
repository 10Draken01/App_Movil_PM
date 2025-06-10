package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun FormButtomCustom(
    fethButtom: Boolean,
    loading: Boolean,
    onClick: () -> Unit,
    enabled: Boolean,
    text: String = if (fethButtom) "Guardar" else "Cancelar",
    modifier: Modifier = Modifier // ← Agregar parámetro modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (fethButtom) Color.DarkGray else Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = modifier // ← Usar el modifier personalizado en lugar de fillMaxWidth()
            .height(50.dp),
        enabled = enabled
    ) {
        if (loading && fethButtom) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color.White, CircleShape)
                        .rotate(rotationAngle)
                        .clip(CircleShape)
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(Color.Gray, CircleShape)
                            .align(Alignment.TopStart)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Guardando...",
                    fontSize = 12.sp,
                    fontFamily = Spooftrial_regular
                )
            }
        } else {
            Text(
                text = text,
                fontSize = 12.sp,
                fontFamily = Spooftrial_bold
            )
        }
    }
}