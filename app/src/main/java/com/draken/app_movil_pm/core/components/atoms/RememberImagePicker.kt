//package com.draken.app_movil_pm.core.components.atoms
//
//import android.net.Uri
//import android.os.Build
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.PickVisualMediaRequest
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.platform.LocalContext
//import com.draken.app_movil_pm.core.domain.model.SimpleImagePickerActions
//import com.draken.app_movil_pm.core.domain.model.SimpleImagePickerState
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import okhttp3.MultipartBody
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun RememberImagePicker(
//    partName: String = "image",
//    onImageReady: (MultipartBody.Part, Uri) -> Unit = { _, _ -> },
//    onError: (String) -> Unit = {},
//    hasRequiredPermissions: () -> Boolean,
//    validateImageUri: (Uri) -> Boolean,
//): SimpleImagePickerActions {
//
//    val context = LocalContext.current
//    val fileManager = remember { FileManager(context) }
//    var state by remember { mutableStateOf(SimpleImagePickerState()) }
//
//    // Launcher moderno para Android 13+
//    val modernPicker = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.PickVisualMedia()
//    ) { uri ->
//        processSelectedImage(uri, validateImageUri, onError) { newState ->
//            state = newState
//        }
//    }
//
//    // Launcher para versiones anteriores
//    val legacyPicker = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri ->
//        processSelectedImage(uri, validateImageUri, onError) { newState ->
//            state = newState
//        }
//    }
//
//    // Launcher para permisos
//    val permissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        if (permissions.values.all { it }) {
//            // Permisos concedidos, abrir galería
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                modernPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//            } else {
//                legacyPicker.launch("image/*")
//            }
//        } else {
//            val errorMsg = "Permisos necesarios para acceder a las imágenes"
//            state = state.copy(error = errorMsg, isLoading = false)
//            onError(errorMsg)
//        }
//    }
//
//    return SimpleImagePickerActions(
//        state = state,
//        pickImage = {
//            if (hasRequiredPermissions()) {
//                // Solicitar permisos
//                permissionLauncher.launch(fileManager.hasRequiredPermissions())
//            } else {
//                // Abrir galería directamente
//                state = state.copy(isLoading = true, error = null)
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    modernPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                } else {
//                    legacyPicker.launch("image/*")
//                }
//            }
//        },
//        clearImage = {
//            state = SimpleImagePickerState()
//            fileManager.cleanupTempFiles()
//        },
//        clearError = {
//            state = state.copy(error = null)
//        }
//    )
//}
//
//private fun processSelectedImage(
//    uri: Uri?,
//    validateImageUri: (Uri) -> Boolean,
//    onError: (String) -> Unit,
//    updateState: (SimpleImagePickerState) -> Unit
//) {
//    if (uri == null) {
//        val errorMsg = "No se seleccionó ninguna imagen"
//        updateState(SimpleImagePickerState(error = errorMsg))
//        onError(errorMsg)
//        return
//    }
//
//    // Validar imagen
//    if (!validateImageUri(uri)) {
//        val errorMsg = "La imagen seleccionada no es válida."
//        updateState(SimpleImagePickerState(error = errorMsg))
//        onError(errorMsg)
//        return
//    }
//
//    // Éxito
//    updateState(SimpleImagePickerState(
//        selectedImageUri = uri,
//        isLoading = false,
//        error = null
//    ))
//}