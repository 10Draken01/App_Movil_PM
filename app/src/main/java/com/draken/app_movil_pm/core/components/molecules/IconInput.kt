package com.draken.app_movil_pm.core.components.molecules

import androidx.compose.runtime.Composable
import com.draken.app_movil_pm.core.components.atoms.IconSelection

@Composable
fun IconInput(
    icons: List<Int>,
    characterIcon: Int,
    onChangeCharacterIcon: (Int) -> Unit
){
    IconSelection(
        icons = icons,
        characterIcon = characterIcon,
        onChangeCharacterIcon = onChangeCharacterIcon
    )
}