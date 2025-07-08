package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

private val TEXT_SIZE_BODY = 12.sp
private val ICON_SIZE_SMALL = 25.dp
private val SPACING_MEDIUM = 15.dp
private val TEXT_SIZE_CAPTION = 10.sp
private val LINE_HEIGHT_CAPTION = 5.sp

@Composable
fun ClienteDetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Unspecified,
            modifier = Modifier.size(ICON_SIZE_SMALL)
        )

        Spacer(modifier = Modifier.width(SPACING_MEDIUM))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontFamily = Spooftrial_regular,
                fontSize = TEXT_SIZE_CAPTION,
                lineHeight = LINE_HEIGHT_CAPTION,
                color = Color.DarkGray
            )
            Text(
                text = value,
                fontFamily = Spooftrial_regular,
                fontSize = TEXT_SIZE_BODY,
                lineHeight = LINE_HEIGHT_CAPTION,
                color = Color.Gray
            )
        }
    }
}