package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.InputType
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms.CustomToggleSwitch
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms.ImageCircleComponent
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms.InputForm
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun Form(
    inputs: List<InputType>,
    icons: List<Int>,
    characterIcon: Int,
    onChangeCharacterIcon: (Int) -> Unit
){
    // CORRECCIÓN: Cambiar 'val' por 'var' y añadir 'setValue'
    var isIcon by remember { mutableStateOf(true) }

    inputs.map { input ->
        InputForm(
            label = input.label,
            placeholder = input.placeholder,
            value = input.value,
            onValueChange = { input.onChange(it) }
        )

        Spacer(modifier = Modifier.height(15.dp))
    }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Character",
            fontSize = 15.sp,
            color = Color.Black,
            fontFamily = Spooftrial_regular,
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomToggleSwitch(
            leftText = "Icon",
            rightText = "Imagen",
            isRightSelected = isIcon,
            onSelectionChange = { t -> isIcon = t}, // Ahora funciona correctamente

        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    if(isIcon){
        IconInput(
            icons = icons,
            characterIcon = characterIcon,
            onChangeCharacterIcon = onChangeCharacterIcon
        )
    } else {

        ImageCircleComponent()
    }
}