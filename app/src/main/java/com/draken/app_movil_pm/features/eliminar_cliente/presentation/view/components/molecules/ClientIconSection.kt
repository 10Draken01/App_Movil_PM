package com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.core.components.atoms.IconCustom
import com.draken.app_movil_pm.core.components.atoms.LabelText
import com.draken.app_movil_pm.core.components.atoms.SectionSpacer
import com.draken.app_movil_pm.core.components.molecules.ImageCircleComponent
import com.draken.app_movil_pm.core.domain.model.Cliente

@Composable
fun ClientIconSection(
    cliente: Cliente,
    icons: List<Int>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LabelText(text = "Icono de Cliente")
        SectionSpacer(height = 18.dp)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (cliente.characterIcon.characterIconUrl != null) {
                ImageCircleComponent(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = cliente.characterIcon.characterIconUrl.url,
                    onDeleteClick = {},
                    justPreview = true
                )
            } else {
                IconCustom(
                    iconRes = icons[cliente.characterIcon.characterIconNumber],
                    size = 48.dp,
                    contentDescription = "Character Icon"
                )
            }
        }
    }
}