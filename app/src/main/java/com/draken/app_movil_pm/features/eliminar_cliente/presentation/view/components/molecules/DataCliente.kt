package com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.molecules

import androidx.compose.foundation.layout.Box
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
import com.draken.app_movil_pm.core.components.atoms.ImageCircleComponent
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.atoms.CharacterIcon
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.atoms.InputForm
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular

@Composable
fun DataCliente( cliente: Cliente, icons: List<Int>){
    Text(
        text = "Clave Cliente",
        fontSize = 13.sp,
        color = Color(23, 23, 23, 255),
        fontFamily = Spooftrial_bold,
    )
    Text(
        text = cliente.claveCliente,
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = Spooftrial_regular
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = "Nombre",
        fontSize = 13.sp,
        color = Color(23, 23, 23, 255),
        fontFamily = Spooftrial_bold,
    )
    Text(
        text = cliente.nombre,
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = Spooftrial_regular
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = "Celular",
        fontSize = 13.sp,
        color = Color(23, 23, 23, 255),
        fontFamily = Spooftrial_bold,
    )
    Text(
        text = cliente.celular,
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = Spooftrial_regular
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = "Email",
        fontSize = 13.sp,
        color = Color(23, 23, 23, 255),
        fontFamily = Spooftrial_bold,
    )
    Text(
        text = cliente.email,
        color = Color.Black,
        fontSize = 12.sp,
        fontFamily = Spooftrial_regular
    )

    Spacer(modifier = Modifier.height(5.dp))

    Text(
        text = "Icono de Cliente",
        fontSize = 13.sp,
        color = Color(23, 23, 23, 255),
        fontFamily = Spooftrial_bold,
    )

    Spacer(modifier = Modifier.height(5.dp))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        if(cliente.characterIcon.characterIconUrl != null){
            ImageCircleComponent(
                imageUrl = cliente.characterIcon.characterIconUrl.url,
                onDeleteClick = {},
                justPreview = true
            )
        } else {
            CharacterIcon(icon = icons[cliente.characterIcon.characterIconNumber])
        }
    }
}