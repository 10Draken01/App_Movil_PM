package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.ButtonIconCustom
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.InputNumberPage
import com.draken.app_movil_pm.ui.theme.Spooftrial_bold
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular
import kotlin.math.ceil
import kotlin.math.min

@Composable
fun ControlesPaginas(
    currentPage: Int,
    pageInputText: String,
    currentSubPage: Int,
    onChangeCurrentSubPage: (it: Int) -> Unit,
    onChangeCurrentPage: (it: Int) -> Unit,
    onChangePageInputText: (it: String) -> Unit,
    filteredClientes: List<Cliente>,
    clientesPerSubPage: Int
){
    val totalSubPages = ceil(filteredClientes.size.toDouble() / clientesPerSubPage).toInt()
    val startIndex = (currentSubPage - 1) * clientesPerSubPage
    val endIndex = min(startIndex + clientesPerSubPage, filteredClientes.size)

    // Determinar el máximo de páginas disponibles (esto depende de tu lógica de negocio)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ){
            // Botón página anterior
            ButtonIconCustom(
                icon = painterResource(id = R.drawable.arrow),
                contentDescription = "Página Anterior",
                color = Color.Black,
                sizeIcon = 20.dp,
                onClick = {
                        val newPage = currentPage + 1
                        onChangeCurrentPage(newPage)
                        onChangePageInputText(newPage.toString())
                        onChangeCurrentSubPage(1) // Resetear a primera subpágina
                },
                rotation = 180f
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(1f)
            ){
                Text(
                    text = "Página:",
                    fontFamily = Spooftrial_bold,
                    fontSize = 10.sp,
                    lineHeight = 5.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(5.dp))

                InputNumberPage(
                    currentPage = currentPage,
                    pageInputText = pageInputText,
                    onChangePageInputText = onChangePageInputText,
                    onChangeCurrentPage = onChangeCurrentPage, // ✅ Pasar esta función
                    onChangeCurrentSubPage = onChangeCurrentSubPage
                )
            }

            // Botón página siguiente
            ButtonIconCustom(
                icon = painterResource(id = R.drawable.arrow),
                contentDescription = "Página Siguiente",
                color = if (currentPage > 1) Color.Black else Color.Gray,
                sizeIcon = 20.dp,
                onClick = {
                    if (currentPage > 1) {
                        val newPage = currentPage - 1
                        onChangeCurrentPage(newPage)
                        onChangePageInputText(newPage.toString())
                        onChangeCurrentSubPage(1) // Resetear a primera subpágina
                    }
                },
                enabled = currentPage > 1
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón subpágina anterior
            ButtonIconCustom(
                icon = painterResource(id = R.drawable.arrow),
                contentDescription = "Subpágina Anterior",
                color = if (currentSubPage > 1) Color.Black else Color.Gray,
                sizeIcon = 20.dp,
                onClick = {
                    if (currentSubPage > 1) {
                        onChangeCurrentSubPage(currentSubPage - 1)
                    }
                },
                rotation = 90f,
                enabled = currentSubPage > 1
            )

            // Información de subpágina
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "${startIndex + 1}-${endIndex} de ${filteredClientes.size}",
                    fontFamily = Spooftrial_regular,
                    fontSize = 10.sp,
                    lineHeight = 5.sp,
                    color = Color.White
                )
            }

            // Botón subpágina siguiente
            ButtonIconCustom(
                icon = painterResource(id = R.drawable.arrow),
                contentDescription = "Subpágina Siguiente",
                color = if (currentSubPage < totalSubPages) Color.Black else Color.Gray,
                sizeIcon = 20.dp,
                onClick = {
                    if (currentSubPage < totalSubPages) {
                        onChangeCurrentSubPage(currentSubPage + 1)
                    }
                },
                enabled = currentSubPage < totalSubPages,
                rotation = 270f
            )
        }
    }
}