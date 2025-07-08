package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun ValueText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    color: Color = Color.Black,
    fontFamily: FontFamily = Spooftrial_regular
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = fontFamily,
        modifier = modifier
    )
}