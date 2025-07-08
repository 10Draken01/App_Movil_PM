package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

private val LINE_HEIGHT_CAPTION = 5.sp
private val TEXT_SIZE_CAPTION = 10.sp
@Composable
fun ClienteInfoRow(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            fontFamily = Spooftrial_regular,
            fontSize = TEXT_SIZE_CAPTION,
            color = Color.DarkGray,
            lineHeight = LINE_HEIGHT_CAPTION
        )
        Text(
            text = value,
            fontFamily = Spooftrial_regular,
            fontSize = TEXT_SIZE_CAPTION,
            color = Color.Gray,
            lineHeight = LINE_HEIGHT_CAPTION,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}