package com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.draken.app_movil_pm.core.components.atoms.SectionSpacer
import com.draken.app_movil_pm.core.components.molecules.LabelValuePair

@Composable
fun ClientInfoField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LabelValuePair(
            label = label,
            value = value
        )
        SectionSpacer()
    }
}