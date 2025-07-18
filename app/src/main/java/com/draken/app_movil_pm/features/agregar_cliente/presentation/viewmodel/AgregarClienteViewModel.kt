package com.draken.app_movil_pm.features.agregar_cliente.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.core.add_cliente_service.domain.usecase.AddClienteUseCase
import com.draken.app_movil_pm.core.connectivity_monitor.domain.ConnectivityMonitorRepository
import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.core.hardware.domain.CameraManagerRepository
import com.draken.app_movil_pm.core.public_app_folder_manager.domain.repository.PublicAppFolderManagerRepository
import com.draken.app_movil_pm.core.domain.model.InputType
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.core.hardware.domain.VibratorRepository
import com.draken.app_movil_pm.core.rooms.domain.usecase.AddLocalClienteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AgregarClienteViewModel(
    private val addClienteUseCase: AddClienteUseCase,
    private val addLocalClienteUseCase: AddLocalClienteUseCase,
    private val cameraManagerRepository: CameraManagerRepository,
    private val publicAppFolderManagerRepository: PublicAppFolderManagerRepository,
    private val vibratorRepository: VibratorRepository,
    private val connectivityMonitorRepository: ConnectivityMonitorRepository
) : ViewModel() {
    private val _claveCliente = MutableStateFlow<String>("")
    val claveCliente: StateFlow<String> = _claveCliente.asStateFlow()

    private val _nombre = MutableStateFlow<String>("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _celular = MutableStateFlow<String>("")
    val celularText: StateFlow<String> = _celular.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _characterIcon = MutableStateFlow<CharacterIcon>(CharacterIcon())
    val characterIcon: StateFlow<CharacterIcon> = _characterIcon.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _stateResponse = MutableStateFlow(Response())
    val stateResponse: StateFlow<Response> = _stateResponse.asStateFlow()

    private val _deviceHasCamera = MutableStateFlow<Boolean>(false)
    val deviceHasCamera: StateFlow<Boolean> = _deviceHasCamera.asStateFlow()

    private val _hasCameraPermission = MutableStateFlow<Boolean>(false)
    val hasCameraPermission: StateFlow<Boolean> = _hasCameraPermission.asStateFlow()

    init {
        viewModelScope.launch {
            _deviceHasCamera.value = cameraManagerRepository.hasCamera()
            _hasCameraPermission.value = cameraManagerRepository.hasCameraPermission()
        }
    }

    fun hasCamera() {
        viewModelScope.launch {
            _deviceHasCamera.value = cameraManagerRepository.hasCamera()
        }
    }

    var icons: List<Int> = listOf(
        R.drawable.estrella,
        R.drawable.edificio,
        R.drawable.caja,
        R.drawable.etiqueta,
        R.drawable.sobre,
        R.drawable.reloj,
        R.drawable.laptop,
        R.drawable.grafica,
        R.drawable.maletin,
        R.drawable.portafolio
    )

    val inputs: List<InputType>
        get() = listOf(
            InputType(
                label = "Clave Cliente",
                placeholder = "000000001",
                value = _claveCliente.value,
                onChange = { onChangeClaveCliente(it) }
            ),
            InputType(
                label = "Nombre",
                placeholder = "Ms. Amanda Osinski IV",
                value = _nombre.value,
                onChange = { onChangeNombre(it) }
            ),
            InputType(
                label = "Celular",
                placeholder = "+1-270-210-3231",
                value = _celular.value,
                onChange = { onChangeCelular(it) }
            ),
            InputType(
                label = "Email",
                placeholder = "mallie@gmail.com",
                value = _email.value,
                onChange = { onChangeEmail(it) }
            ),
        )

    fun vibrate() {
        viewModelScope.launch {
            if (vibratorRepository.hasVibrator()) {
                vibratorRepository.vibrate()
            }
        }
    }

    fun agregarCliente() {
        // Limpiar error anterior
        _stateResponse.value = Response()

        if (!validateData()) {
            _stateResponse.value = Response(error = "Hay campos vacíos")
            vibrate()
            return
        }

        if (!isValidEmail(_email.value)) {
            _stateResponse.value =
                Response(error = "Por favor ingresa un correo electrónico válido")
            vibrate()
            return
        }

        try {
            _loading.value = true
            val isConnected = connectivityMonitorRepository.isCurrentlyConnected()
            if (isConnected) {
                agregarAPI()
            } else {
                agregarLocal()
            }
        } catch (e: Exception) {
            Log.e("AgregarClienteViewModel", "Error: ${e.message}")
            vibrate()
            _stateResponse.value = Response(error = "Error de conexión. Inténtalo de nuevo.")
        } finally {
            _loading.value = false
        }

    }

    private fun agregarAPI() {
        viewModelScope.launch {
            val result: Response = addClienteUseCase(
                _claveCliente.value.trim(),
                _nombre.value,
                _celular.value.trim(),
                _email.value.trim(),
                _characterIcon.value
            )

            if (result.error.isNullOrEmpty()) {
                _stateResponse.value =
                    Response(message = "${_nombre.value} se agrego correctamente")
            } else {
                _stateResponse.value = result
            }
        }
    }

    private fun agregarLocal(){
        viewModelScope.launch {

            val result: Response = addLocalClienteUseCase(
                    cliente = Cliente(
                        claveCliente = _claveCliente.value.trim(),
                        nombre = _nombre.value,
                        celular = _celular.value.trim(),
                        email = _email.value.trim(),
                        characterIcon = _characterIcon.value,
                        isLocal = true
                    )
                )
            if (result.error.isNullOrEmpty()) {
                _stateResponse.value =
                    Response(message = "${_nombre.value} se agrego correctamente")
            } else {
                _stateResponse.value = result
            }
        }
    }

    fun updateHasCameraPermission() {
        viewModelScope.launch {
            _hasCameraPermission.value = cameraManagerRepository.hasCameraPermission()
        }
    }

    fun createImageUri(onUriCreated: (Uri?) -> Unit) {
        viewModelScope.launch {
            try {
                if (_claveCliente.value.isNotEmpty() && _nombre.value.isNotEmpty()) {
                    val fileName = "${_claveCliente.value}_${_nombre.value}.jpg"

                    val uri = publicAppFolderManagerRepository.createImageUri(
                        fileName,
                        "ClientesImages"
                    )

                    if (uri != null) {
                        onUriCreated(uri) // ← Callback con resultado exitoso
                    } else {
                        vibrate()
                        _stateResponse.value = Response(error = "Error al crear el archivo")
                        onUriCreated(null) // ← Callback con error
                    }
                } else {
                    vibrate()
                    _stateResponse.value = Response(error = "Campos requeridos")
                    onUriCreated(null)
                }
            } catch (e: Exception) {
                vibrate()
                _stateResponse.value = Response(error = "Error: ${e.message}")
                onUriCreated(null)
            }
        }
    }

    private fun validateData(): Boolean {
        return _claveCliente.value.isNotBlank() &&
                _nombre.value.isNotBlank() &&
                _celular.value.isNotBlank() &&
                _email.value.trim().isNotBlank()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    fun onChangeClaveCliente(claveCliente: String) {
        _claveCliente.value = claveCliente
        clearErrorOnChangeValue()
    }

    fun onChangeNombre(nombre: String) {
        _nombre.value = nombre
        clearErrorOnChangeValue()
    }

    fun onChangeCelular(celular: String) {
        _celular.value = celular
        clearErrorOnChangeValue()
    }

    fun onChangeEmail(email: String) {
        _email.value = email
        clearErrorOnChangeValue()
    }

    fun onChangeCharacterIconNumber(icon: Int) {
        _characterIcon.value = CharacterIcon(characterIconNumber = icon)
        clearErrorOnChangeValue()
    }

    fun onChangeCharacterIconUri(uri: Uri?) {
        _characterIcon.value = CharacterIcon(characterIconUri = uri)
        clearErrorOnChangeValue()
    }

    fun clearErrorOnChangeValue() {
        // Limpiar error cuando el usuario empiece a escribir
        if (_stateResponse.value.error != null) {
            _stateResponse.value = Response()
        }
    }
}