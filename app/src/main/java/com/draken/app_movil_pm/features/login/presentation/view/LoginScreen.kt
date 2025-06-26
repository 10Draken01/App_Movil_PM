package com.draken.app_movil_pm.features.login.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.features.login.presentation.viewmodel.LoginViewModel
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.draken.app_movil_pm.features.login.di.AppModule
import com.draken.app_movil_pm.features.login.presentation.viewmodel.LoginViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(AppModule.loginUseCase)
    ),
    onNavigateToRegister: () -> Unit = {},
    onNavigateToClientes: () -> Unit = {}
) {
    val email by viewModel.emailText.collectAsState()
    val password by viewModel.passwordText.collectAsState()
    val stateResponse by viewModel.stateResponse.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val scrollState = rememberScrollState()

    // Animación de rotación para el indicador de carga
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

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
            .fillMaxSize()
            .background(Color(231, 231, 231, 255))
            .padding(top = 50.dp, end = 40.dp, start = 40.dp, bottom = 50.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la app",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Block Solutions",
            fontFamily = Spooftrial_bold,
            color = Color.Black,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Iniciar Sesión",
            fontFamily = Spooftrial_bold,
            color = Color.Black,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        InputForm(
            label = "Correo electrónico",
            placeholder = "ejemplo@gmail.com",
            value = email,
            onValueChange = viewModel::onChangeEmailText
        )

        Spacer(modifier = Modifier.height(15.dp))

        InputForm(
            label = "Contraseña",
            placeholder = "........",
            value = password,
            onValueChange = viewModel::onChangePasswordText,
            isPassword = true
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

        // Botón con animación de carga - simplificado
        Button(
            onClick = {
                viewModel.login()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (loading) Color.Gray else Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            enabled = !loading && email.trim().isNotBlank() && password.isNotBlank() &&
                    android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
        ) {
            if (loading) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.White, CircleShape)
                            .rotate(rotationAngle)
                            .clip(CircleShape)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(Color.Gray, CircleShape)
                                .align(Alignment.TopStart)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Iniciando sesión...",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontFamily = Spooftrial_regular
                    )
                }
            } else {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontFamily = Spooftrial_bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿Olvidaste tu contraseña?",
            fontFamily = Spooftrial_regular,
            fontSize = 10.sp,
            color = Color.Black,
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                // Acción para recuperar contraseña
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(10f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "o",
                fontFamily = Spooftrial_regular,
                fontSize = 10.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .weight(10f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿No tienes una cuenta?",
            fontFamily = Spooftrial_regular,
            color = Color.Black,
            fontSize = 10.sp
        )
        Text(
            text = "Registrarse",
            fontSize = 10.sp,
            fontFamily = Spooftrial_bold,
            color = Color.Black,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onNavigateToRegister()
                }
        )
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

@Composable
fun InputForm(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Text(
        text = label,
        fontSize = 12.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontFamily = Spooftrial_bold,
    )

    Spacer(modifier = Modifier.height(5.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = Spooftrial_regular
            ),
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 0.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontFamily = Spooftrial_regular
                            )
                        }
                        innerTextField()
                    }

                    if (isPassword) {
                        val image = if (passwordVisible)
                            painterResource(id = R.drawable.vista)
                        else
                            painterResource(id = R.drawable.ojos)

                        Icon(
                            painter = image,
                            contentDescription = if (passwordVisible) "Ocultar" else "Mostrar",
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    passwordVisible = !passwordVisible
                                }
                                .size(20.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        )
    }
}