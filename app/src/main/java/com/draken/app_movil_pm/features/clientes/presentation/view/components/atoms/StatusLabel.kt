package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold

// Constantes para mantener consistencia en el diseño
private val TEXT_SIZE_LABEL = 9.sp
private val LABEL_BORDER_RADIUS = 4.dp
@Composable
fun StatusLabel(isLocal: Boolean) {
    val (text, backgroundColor, textColor) = if (isLocal) {
        Triple("Local", Color.Gray.copy(alpha = 0.2f), Color.DarkGray)
    } else {
        Triple("Nube", Color.Green.copy(alpha = 0.2f), Color(0xFF2E7D32))
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(LABEL_BORDER_RADIUS))
            .background(backgroundColor)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            fontFamily = Spooftrial_bold,
            fontSize = TEXT_SIZE_LABEL,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}