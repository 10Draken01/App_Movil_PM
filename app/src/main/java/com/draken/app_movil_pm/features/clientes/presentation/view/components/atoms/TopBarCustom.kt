package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(
    onNavigateToAgregarCliente: () -> Unit
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.White,

        ),
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "Clientes",
                    fontFamily = Spooftrial_bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                // Botón para agregar cliente
                ButtonIconCustom(
                    icon = painterResource(id = R.drawable.agregar),
                    contentDescription = "Agregar Cliente",
                    color = Color.Black,
                    sizeIcon = 24.dp,
                    onClick = { onNavigateToAgregarCliente() }
                )
            }
        }
    )
}