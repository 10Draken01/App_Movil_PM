package com.draken.app_movil_pm.features.register.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.draken.app_movil_pm.features.register.presentation.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = viewModel(),
                   onNavigateToLogin: () -> Unit = {},
                   onNavigateToHome: () -> Unit = {}
) {
    val username by viewModel.usernameText.collectAsState()
    val email by viewModel.emailText.collectAsState()
    val password by viewModel.passwordText.collectAsState()
    val confirmPassword by viewModel.confirmPasswordText.collectAsState()

    val scrollState = rememberScrollState()
    // Estado para mostrar/ocultar contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        InputForm(
            label = "Nombre de usuario",
            placeholder = "Juan24",
            value = username,
            onValueChange = viewModel::onChangeUsernameText
        )

        Spacer(modifier = Modifier.height(15.dp))

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
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
            showVisibilityToggle = true
        )

        Spacer(modifier = Modifier.height(15.dp))

        InputForm(
            label = "Confirmar contraseña",
            placeholder = "........",
            value = confirmPassword,
            onValueChange = viewModel::onChangeConfirmPasswordText,
            isPassword = true,
            passwordVisible = passwordVisible,
            showVisibilityToggle = false
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* acción */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text("Iniciar sesión", fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿Ya tienes una cuenta?",
            fontFamily = Spooftrial_regular,
            fontSize = 10.sp
        )
        Text(
            text = "Iniciar Secion",
            fontSize = 10.sp,
            fontFamily = Spooftrial_bold,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onNavigateToLogin()
                }
        )
    }
}

@Composable
fun InputForm(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: (() -> Unit)? = null,
    showVisibilityToggle: Boolean = false
) {
    Text(
        text = label,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Spooftrial_bold,
    )

    Spacer(modifier = Modifier.height(5.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
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

                    if (isPassword && showVisibilityToggle && onPasswordVisibilityChange != null) {
                        val image = if (passwordVisible)
                            painterResource(id = R.drawable.vista)
                        else
                            painterResource(id = R.drawable.ojos)

                        Icon(
                            painter = image,
                            contentDescription = if (passwordVisible) "Ocultar" else "Mostrar",
                            modifier = Modifier
                                .clickable {
                                    onPasswordVisibilityChange()
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