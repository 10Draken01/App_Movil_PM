package com.draken.app_movil_pm.features.editar_cliente.presentation.view

import android.Manifest
import android.net.Uri
import android.util.Log
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
import com.draken.app_movil_pm.core.components.atoms.FormButtomCustom
import com.draken.app_movil_pm.core.components.atoms.InputForm
import com.draken.app_movil_pm.core.di.AppFolderModule
import com.draken.app_movil_pm.core.di.HardwareModule
import com.draken.app_movil_pm.features.editar_cliente.di.EditarClienteModule
import com.draken.app_movil_pm.features.editar_cliente.presentation.viewmodel.EditarClienteViewModel
import com.draken.app_movil_pm.features.editar_cliente.presentation.viewmodel.EditarClienteViewModelFactory
import com.draken.app_movil_pm.core.navigation.SharedDataViewModel
import com.draken.app_movil_pm.features.editar_cliente.presentation.view.components.molecules.FormEditarCliente
import kotlinx.coroutines.delay

@Composable
fun EditarClienteScreen(
    sharedViewModel: SharedDataViewModel,
    viewModel: EditarClienteViewModel = viewModel(
        factory = EditarClienteViewModelFactory(
            editarClienteUseCase = EditarClienteModule.editarClienteUseCase,
            cameraManagerRepository = HardwareModule.cameraRepository,
            publicAppFolderManagerRepository = AppFolderModule.publicAppFolderManagerRepository
        )
    ),
    onNavigateToClientes: () -> Unit = {}
) {
    var isIcon by remember { mutableStateOf<Boolean>(true) }
    // Observar el cliente seleccionado correctamente
    val selectedCliente by sharedViewModel.selectedCliente.collectAsState()
    val deviceHasCamera by viewModel.deviceHasCamera.collectAsState()
    val hasCameraPermission by viewModel.hasCameraPermission.collectAsState()
    var uriTemp by remember { mutableStateOf<Uri?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        succes ->
        viewModel.updateHasCameraPermission()
        if(!succes){
            viewModel.onChangeIsIcon(false)
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

    // Inicializar el ViewModel cuando se recibe un cliente
    LaunchedEffect(selectedCliente) {
        selectedCliente?.let { cliente ->
            viewModel.setCliente(cliente)
        }
    }

    val claveCliente by viewModel.claveCliente.collectAsState()
    val nombre by viewModel.nombreText.collectAsState()
    val celular by viewModel.celular.collectAsState()
    val email by viewModel.email.collectAsState()
    val characterIcon by viewModel.characterIcon.collectAsState()

    val icons = viewModel.icons
    val inputs = viewModel.inputs

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
                text = "Actualizar Cliente",
                fontFamily = Spooftrial_bold,
                color = Color.Black,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            InputForm(
                label = "Clave Cliente",
                placeholder = "000000001",
                value = claveCliente,
                onValueChange = { },
                enable = false
            )

            Spacer(modifier = Modifier.height(10.dp))

            FormEditarCliente(
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
                        viewModel.editarCliente()
                    },
                    enabled = !loading &&
                            claveCliente.isNotBlank() &&
                            nombre.isNotBlank() &&
                            celular.isNotBlank() &&
                            email.trim().isNotBlank() &&
                            android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches(),
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