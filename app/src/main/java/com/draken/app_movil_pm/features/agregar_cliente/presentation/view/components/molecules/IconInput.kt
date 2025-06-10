package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms.IconSelection
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun IconInput(
    icons: List<Int>,
    characterIcon: Int,
    onChangeCharacterIcon: (Int) -> Unit
){
    Text(
        text = "Icono de Cliente",
        fontSize = 15.sp,
        fontFamily = Spooftrial_regular,
    )

    Spacer(modifier = Modifier.height(5.dp))

    IconSelection(
        icons = icons,
        characterIcon = characterIcon,
        onChangeCharacterIcon = onChangeCharacterIcon
    )

    Spacer(modifier = Modifier.height(15.dp))
}