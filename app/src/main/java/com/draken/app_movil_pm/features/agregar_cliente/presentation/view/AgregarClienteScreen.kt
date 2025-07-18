package com.draken.app_movil_pm.features.agregar_cliente.presentation.view

import android.Manifest
import android.net.Uri
import android.util.Log
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.draken.app_movil_pm.core.add_cliente_service.di.AddClienteModule
import com.draken.app_movil_pm.core.di.AppFolderModule
import com.draken.app_movil_pm.core.di.HardwareModule
import com.draken.app_movil_pm.core.components.atoms.FormButtomCustom
import com.draken.app_movil_pm.core.di.ConnectivityMonitorModule
import com.draken.app_movil_pm.core.di.LocalClientesModule
import com.draken.app_movil_pm.features.agregar_cliente.presentation.view.components.organims.FormAgregarCliente
import com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel.AgregarClienteViewModel
import com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel.AgregarClienteViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun AgregarClienteScreen(
    viewModel: AgregarClienteViewModel = viewModel(
        factory = AgregarClienteViewModelFactory(
            addClienteUseCase = AddClienteModule.addClienteUseCase,
            addLocalClienteUseCase = LocalClientesModule.addLocalClienteUseCase,
            cameraManagerRepository = HardwareModule.cameraRepository,
            publicAppFolderManagerRepository = AppFolderModule.publicAppFolderManagerRepository,
            vibratorRepository = HardwareModule.vibratorRepository,
            connectivityMonitorRepository = ConnectivityMonitorModule.connectivityMonitorRepository
        )
    ),
    onNavigateToClientes: () -> Unit = {}
) {
    var isIcon by remember { mutableStateOf<Boolean>(true) }

    val claveCliente by viewModel.claveCliente.collectAsState()
    val nombre by viewModel.nombre.collectAsState()
    val celular by viewModel.celularText.collectAsState()
    val email by viewModel.email.collectAsState()
    val characterIcon by viewModel.characterIcon.collectAsState()
    val deviceHasCamera by viewModel.deviceHasCamera.collectAsState()
    val hasCameraPermission by viewModel.hasCameraPermission.collectAsState()
    var uriTemp by remember { mutableStateOf<Uri?>(null) }

    val icons = viewModel.icons
    val inputs = viewModel.inputs

    val stateResponse by viewModel.stateResponse.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val scrollState = rememberScrollState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { success ->
        viewModel.updateHasCameraPermission()
        if (!success) {
            isIcon = false
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        Log.d("Camera", "Resultado de la cámara: $isSuccess")

        if (isSuccess) {
            Log.d("Camera", "Foto tomada exitosamente.")
            viewModel.onChangeCharacterIconUri(uriTemp)
        } else {
            Log.e("Camera", "Error al tomar la foto o el usuario canceló")
            // Opcional: Limpiar el URI si falló
            viewModel.onChangeCharacterIconUri(null)
        }
    }

    // Efecto para manejar la navegación cuando el login es exitoso
    LaunchedEffect(stateResponse) {
        if (!loading && stateResponse.error.isNullOrEmpty() && !stateResponse.message.isNullOrEmpty()) {
            // Login exitoso, esperar 1 segundo y navegar
            delay(1000L)
            onNavigateToClientes()
        }
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
            Text(
                text = "Agregar Nuevo Cliente",
                fontFamily = Spooftrial_bold,
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            FormAgregarCliente(
                isIcon = isIcon,
                hasCamera = deviceHasCamera,
                inputs = inputs,
                icons = icons,
                characterIcon = characterIcon,
                onChangeCharacterIconNumber = viewModel::onChangeCharacterIconNumber,
                onChangeCharacterIconUri = viewModel::onChangeCharacterIconUri,
                onChangeIsIcon = { t -> isIcon = t },
                takePhoto = {
                    Log.d("Camera", "Tomar Foto")
                    viewModel.hasCamera()
                    if (deviceHasCamera) {
                        if (!hasCameraPermission) {
                            Log.d("Camera", "No hay permiso para tomar foto")
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        } else {
                            Log.d("Camera", "Si hay permiso para tomar foto")
                            // SOLUCIÓN: Usar callback para esperar el URI
                            viewModel.createImageUri { uri ->
                                uriTemp = uri
                                if (uri != null) {
                                    Log.d("Camera", "URI disponible: $uri")
                                    try {
                                        cameraLauncher.launch(uri)
                                    } catch (e: Exception) {
                                        Log.e("Camera", "Error al lanzar cámara: ${e.message}")
                                    }
                                } else {
                                    Log.e("Camera", "Error: URI es null")
                                }
                            }
                        }
                    }
                }
            )

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
                horizontalArrangement = Arrangement.spacedBy(10.dp) // ← Espaciado uniforme entre botones
            ) {
                FormButtomCustom(
                    fethButtom = true,
                    loading = loading,
                    onClick = {
                        viewModel.agregarCliente()
                    },
                    enabled = !loading &&
                            claveCliente.isNotBlank() &&
                            nombre.isNotBlank() &&
                            celular.isNotBlank() &&
                            email.trim().isNotBlank() &&
                            Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches(),
                    modifier = Modifier.weight(1f), // ← Cada botón ocupa el mismo espacio,
                    textColor = Color.White,
                    bacgroundColor = Color.DarkGray,
                    disableTextColor = Color.White,
                    disableBacgroundColor = Color.Gray
                )

                FormButtomCustom(
                    fethButtom = false,
                    loading = loading,
                    onClick = { onNavigateToClientes() },
                    enabled = true,
                    modifier = Modifier.weight(1f), // ← Cada botón ocupa el mismo espacio
                    textColor = Color.White,
                    bacgroundColor = Color.Black
                )
            }
        }
    }
}

// Función para mejorar los mensajes de éxito
private fun getSuccessMessage(message: String): String {
    return when {
        message.contains("login", ignoreCase = true) -> "✅ ¡Inicio de sesión exitoso! Bienvenido."
        message.contains("success", ignoreCase = true) -> "✅ ¡Operación completada con éxito!"
        message.isNotEmpty() -> "✅ $message"
        else -> "✅ ¡Operación exitosa!"
    }
}
