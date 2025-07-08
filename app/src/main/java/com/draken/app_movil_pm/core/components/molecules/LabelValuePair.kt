package com.draken.app_movil_pm.core.components.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.draken.app_movil_pm.core.components.atoms.LabelText
import com.draken.app_movil_pm.core.components.atoms.ValueText

@Composable
fun LabelValuePair(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    labelStyle: @Composable (String) -> Unit = { LabelText(text = it) },
    valueStyle: @Composable (String) -> Unit = { ValueText(text = it) }
) {
    Column(modifier = modifier) {
        labelStyle(label)
        valueStyle(value)
    }
}