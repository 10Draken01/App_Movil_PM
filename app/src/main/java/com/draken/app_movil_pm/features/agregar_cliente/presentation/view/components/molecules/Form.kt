package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.InputType
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
    val isIcon by remember { mutableStateOf(false) }

    inputs.map { input ->
        InputForm(
            label = input.label,
            placeholder = input.placeholder,
            value = input.value,
            onValueChange = { input.onChange(it) }
        )

        Spacer(modifier = Modifier.height(15.dp))
    }


    Text(
        text = "Character",
        fontSize = 15.sp,
        color = Color.Black,
        fontFamily = Spooftrial_regular,
    )

    Spacer(modifier = Modifier.height(5.dp))


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