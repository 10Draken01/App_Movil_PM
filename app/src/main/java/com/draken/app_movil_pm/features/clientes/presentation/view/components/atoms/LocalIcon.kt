package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun LocalIcon(
    iconRes: Int,
    iconSize: Dp,
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = "Profile Icon",
        tint = Color.Unspecified,
        modifier = modifier.size(iconSize)
    )
}