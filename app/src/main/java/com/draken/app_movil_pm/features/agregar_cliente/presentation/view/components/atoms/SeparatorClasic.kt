package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun SeparatorClasic(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(10f)
                .height(1.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "o",
            fontFamily = Spooftrial_regular,
            fontSize = 10.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .weight(10f)
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}