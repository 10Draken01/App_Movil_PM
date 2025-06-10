package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold

@Composable
fun InputNumberPage(
    currentPage: Int,
    pageInputText: String,
    onChangePageInputText: (it: String) -> Unit,
    onChangeCurrentPage: (it: Int) -> Unit, // ✅ Nueva función necesaria
    onChangeCurrentSubPage: (it: Int) -> Unit,
){
    var showPageInput by remember { mutableStateOf(false) }

    if (showPageInput) {
        BasicTextField(
            value = pageInputText,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } && newValue.length <= 3) {
                    onChangePageInputText(newValue)
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.Black,
                fontFamily = Spooftrial_bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .width(40.dp)
                .height(24.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(4.dp)
                )
                .border(
                    1.dp,
                    Color.Black,
                    RoundedCornerShape(4.dp)
                ),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    val newPage = pageInputText.toIntOrNull()
                    if (newPage != null && newPage > 0) {
                        // ✅ Actualizar currentPage correctamente
                        onChangeCurrentPage(newPage)
                        onChangeCurrentSubPage(1) // Resetear a primera subpágina
                    }
                    showPageInput = false
                    onChangePageInputText("")
                }
            )
        )
    } else {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(24.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(4.dp)
                )
                .clickable {
                    showPageInput = true
                    onChangePageInputText(currentPage.toString())
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentPage.toString(),
                fontFamily = Spooftrial_bold,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
    }
}