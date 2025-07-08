package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold

@Composable
fun LabelText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    color: Color = Color(23, 23, 23, 255),
    fontFamily: FontFamily = Spooftrial_bold
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = fontFamily,
        modifier = modifier
    )
}