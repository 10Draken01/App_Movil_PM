package com.draken.app_movil_pm.features.eliminar_cliente.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.draken.app_movil_pm.core.components.atoms.IconCustom
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.eliminar_cliente.di.EliminarClienteModule
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.atoms.FormButtomCustom
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.view.components.organims.DataCliente
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.viewmodel.EliminarClienteViewModel
import com.draken.app_movil_pm.features.eliminar_cliente.presentation.viewmodel.EliminarClienteViewModelFactory
import com.draken.app_movil_pm.core.navigation.presentation.viewmodel.SharedDataViewModel
import kotlinx.coroutines.delay

@Composable
fun EliminarClienteScreen(
    sharedViewModel: SharedDataViewModel,
    viewModel: EliminarClienteViewModel = viewModel(
        factory = EliminarClienteViewModelFactory (EliminarClienteModule.eliminarClienteUseCase)
    ),
    onNavigateToClientes: () -> Unit = {}
) {
    // Observar el cliente seleccionado correctamente
    val selectedCliente by sharedViewModel.selectedCliente.collectAsState()

    // Inicializar el ViewModel cuando se recibe un cliente
    LaunchedEffect(selectedCliente) {
        selectedCliente?.let { cliente ->
            viewModel.setCliente(cliente)
        }
    }

    val claveCliente by viewModel.claveClienteText.collectAsState()
    val nombre by viewModel.nombreText.collectAsState()
    val celular by viewModel.celularText.collectAsState()
    val email by viewModel.emailText.collectAsState()
    val characterIcon by viewModel.characterIcon.collectAsState()

    val icons = viewModel.icons
    val data = Cliente(
        claveCliente = claveCliente,
        nombre = nombre,
        celular = celular,
        email = email,
        characterIcon = characterIcon
    )

    val stateResponse by viewModel.stateResponse.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val scrollState = rememberScrollState()

    // Efecto para manejar la navegación cuando la edición es exitosa
    LaunchedEffect(stateResponse) {
        if (!loading && stateResponse.error.isNullOrEmpty() && !stateResponse.message.isNullOrEmpty()) {
            // Edición exitosa, esperar 1 segundo y navegar
            delay(1000L)
            sharedViewModel.clearSelectedCliente() // Limpiar el cliente seleccionado
            onNavigateToClientes()
        }
    }

    // Mostrar loading o mensaje si no hay cliente seleccionado
    if (selectedCliente == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cargando datos del cliente...",
                fontFamily = Spooftrial_regular
            )
        }
        return
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(229, 229, 229, 255),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(5.dp),
                    color = Color(0, 0, 0)
                )
                .padding(top = 20.dp, end = 20.dp, start = 20.dp, bottom = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            IconCustom(
                iconRes = android.R.drawable.ic_dialog_alert,
                size = 50.dp,
                contentDescription = "Alert Icon",
                tint = Color.Black
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Confirmar eliminación",
                fontFamily = Spooftrial_bold,
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "¿Está seguro que desea eliminar este cliente? Esta acción no se puede deshacer.",
                fontFamily = Spooftrial_regular,
                color = Color.Black,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            DataCliente(cliente = data, icons = icons)

            Spacer(modifier = Modifier.height(10.dp))

            // Mensajes de error mejorados con animaciones
            AnimatedVisibility(
                visible = !stateResponse.error.isNullOrEmpty(),
                enter = slideInVertically(animationSpec = tween(300)) + fadeIn(),
                exit = slideOutVertically(animationSpec = tween(300)) + fadeOut()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_dialog_alert),
                            contentDescription = "Error",
                            tint = Color.Red,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stateResponse.error ?: "",
                            fontFamily = Spooftrial_regular,
                            fontSize = 8.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            // Mensajes de éxito mejorados con animaciones
            AnimatedVisibility(
                visible = !stateResponse.message.isNullOrEmpty(),
                enter = slideInVertically(animationSpec = tween(300)) + fadeIn(),
                exit = slideOutVertically(animationSpec = tween(300)) + fadeOut()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E8)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_dialog_info),
                            contentDescription = "Éxito",
                            tint = Color(46, 128, 34, 255),
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = getSuccessMessage(stateResponse.message ?: ""),
                            fontFamily = Spooftrial_regular,
                            fontSize = 8.sp,
                            color = Color(46, 128, 34, 255),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            if (!stateResponse.error.isNullOrEmpty() || !stateResponse.message.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FormButtomCustom(
                    fethButtom = true,
                    loading = loading,
                    onClick = {
                        viewModel.eliminarCliente()
                    },
                    enabled = !loading,
                    modifier = Modifier.weight(1f)
                )

                FormButtomCustom(
                    fethButtom = false,
                    loading = loading,
                    onClick = {
                        sharedViewModel.clearSelectedCliente()
                        onNavigateToClientes()
                    },
                    enabled = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Función para mejorar los mensajes de éxito
private fun getSuccessMessage(message: String): String {
    return when {
        message.contains("agrego", ignoreCase = true) -> "✅ ¡Cliente actualizado exitosamente!"
        message.contains("success", ignoreCase = true) -> "✅ ¡Operación completada con éxito!"
        message.isNotEmpty() -> "✅ $message"
        else -> "✅ ¡Operación exitosa!"
    }
}