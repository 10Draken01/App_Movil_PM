package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp


private const val LOADING_ANIMATION_DURATION = 1000
private val LOADING_INDICATOR_SIZE = 24.dp
private val LOADING_STROKE_WIDTH = 3.dp

@Composable
fun LoadingIndicator(
    color: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading_rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = LOADING_ANIMATION_DURATION,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "loading_angle"
    )

    CircularProgressIndicator(
        modifier = modifier
            .size(LOADING_INDICATOR_SIZE)
            .rotate(angle),
        color = color,
        strokeWidth = LOADING_STROKE_WIDTH,
        strokeCap = StrokeCap.Round
    )
}