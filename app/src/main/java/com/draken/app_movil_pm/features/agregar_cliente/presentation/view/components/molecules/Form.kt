package com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.features.agregar_cliente.domain.model.InputType
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.atoms.InputForm

@Composable
fun Form( inputs: List<InputType>){
    inputs.map { input ->
        InputForm(
            label = input.label,
            placeholder = input.placeholder,
            value = input.value,
            onValueChange = { input.onChange(it) }
        )

        Spacer(modifier = Modifier.height(15.dp))
    }

}