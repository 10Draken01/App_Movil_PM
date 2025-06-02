package com.draken.app_movil_pm.features.login.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.draken.app_movil_pm.features.login.presentation.viewmodel.LoginViewModel
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(),
                onNavigateToRegister: () -> Unit = {},
                onNavigateToHome: () -> Unit = {}
) {
    val email by viewModel.emailText.collectAsState()
    val password by viewModel.passwordText.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, end = 40.dp, start = 40.dp),
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
            text = "¿Olvidaste tu contraseña?",
            fontFamily = Spooftrial_regular,
            fontSize = 10.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .weight(10f)    // ancho completo
                    .height(4.dp)        // altura de la barra
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "o",
                fontFamily = Spooftrial_regular,
                fontSize = 10.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Box(
                modifier = Modifier
                    .weight(10f)   // ancho completo
                    .height(4.dp)        // altura de la barra
                    .background(Color.Gray)
            )
        }

        Text(
            text = "¿No tienes una cuenta?",
            fontFamily = Spooftrial_regular,
            fontSize = 10.sp
        )
        Text(
            text = "Registrarse",
            fontSize = 10.sp,
            fontFamily = Spooftrial_bold,
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

@Composable
fun InputForm(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    // Estado para mostrar/ocultar contraseña
    var passwordVisible by remember { mutableStateOf(false) }

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
            .height(48.dp) // Altura fija para consistencia
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
                .padding(horizontal = 12.dp), // Solo padding horizontal
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 0.dp) // Sin padding adicional aquí
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        // Placeholder
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontFamily = Spooftrial_regular
                            )
                        }
                        // Campo de texto
                        innerTextField()
                    }

                    // Icono de mostrar/ocultar contraseña
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