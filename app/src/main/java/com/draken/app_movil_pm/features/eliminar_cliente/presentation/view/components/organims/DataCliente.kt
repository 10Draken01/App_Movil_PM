package com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.organims

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.core.components.molecules.ImageCircleComponent
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.atoms.CharacterIcon
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.molecules.ClientIconSection
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.molecules.ClientInfoField
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun DataCliente(
    cliente: Cliente,
    icons: List<Int>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ClientInfoField(
            label = "Clave Cliente",
            value = cliente.claveCliente
        )

        ClientInfoField(
            label = "Nombre",
            value = cliente.nombre
        )

        ClientInfoField(
            label = "Celular",
            value = cliente.celular
        )

        ClientInfoField(
            label = "Email",
            value = cliente.email
        )

        ClientIconSection(
            cliente = cliente,
            icons = icons
        )
    }
}