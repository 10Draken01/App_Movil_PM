package com.draken.app_movil_pm.features.editar_cliente.presentation.view.components.atoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IconSelection(
    icons: List<Int>,
    characterIcon: Int,
    onChangeCharacterIcon: (Int) -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ButtonArrow(
            rotation = 180f,
            onClick = {
                if( characterIcon == 0){
                    onChangeCharacterIcon(9)
                } else if( characterIcon <= 9 ){
                    onChangeCharacterIcon(characterIcon - 1)
                }
            }
        )

        Spacer(modifier = Modifier.width(5.dp))

        CharacterIcon(
            icon = icons[characterIcon]
        )

        Spacer(modifier = Modifier.width(5.dp))

        ButtonArrow(
            onClick = {

                if( characterIcon == 9){
                    onChangeCharacterIcon(0)
                } else if( characterIcon >= 0 ){
                    onChangeCharacterIcon(characterIcon + 1)
                }
            }
        )
    }
}