package com.draken.app_movil_pm.features.clientes.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.draken.app_movil_pm.R
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.draken.app_movil_pm.core.di.ConnectivityMonitorModule
import com.draken.app_movil_pm.core.di.LocalClientesModule
import com.draken.app_movil_pm.core.domain.model.Cliente
import com.draken.app_movil_pm.features.clientes.di.ClientesModule
import com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms.TopBarCustom
import com.draken.app_movil_pm.features.clientes.presentation.view.components.organims.ClienteCard
import com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules.ControlesPaginas
import com.draken.app_movil_pm.features.clientes.presentation.viewmodel.ClientesViewModel
import com.draken.app_movil_pm.features.clientes.presentation.viewmodel.ClientesViewModelFactory
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientesScreen(
    viewModel: ClientesViewModel = viewModel(
        factory = ClientesViewModelFactory (
            getClientesPageUseCase = ClientesModule.getClientesPageUseCase,
            getLocalClientesPageUseCase = LocalClientesModule.getLocalClientesPageUseCase,
            connectivityMonitorRepository = ConnectivityMonitorModule.connectivityMonitorRepository
        )
    ),
    onNavigateToAgregarCliente: () -> Unit = {},
    onNavigateToEditarCliente: (cliente: Cliente) -> Unit = {},
    onNavigateToEliminarCliente: (cliente: Cliente) -> Unit = {},
) {
    // Estados de paginación
    var currentPage by remember { mutableIntStateOf(1) }
    var currentSubPage by remember { mutableIntStateOf(1) }
    var pageInputText by remember { mutableStateOf("") }

    val searchText by viewModel.searchText.collectAsState()
    val scrollState = rememberScrollState()
    val icons = viewModel.icons

    // Lista completa de clientes (simulando 100 clientes por página)
    val clientes by viewModel.clientes.collectAsState()

    // Filtrar clientes por búsqueda
    val filteredClientes = remember(searchText, clientes) {
        if (searchText.isEmpty()) {
            clientes
        } else {
            clientes.filter { cliente ->
                cliente.claveCliente.contains(searchText, ignoreCase = true) ||
                        cliente.nombre.contains(searchText, ignoreCase = true)
            }
        }
    }

    // Calcular paginación (10 clientes por sub-página)
    val clientesPerSubPage = 10
    val startIndex = (currentSubPage - 1) * clientesPerSubPage
    val endIndex = min(startIndex + clientesPerSubPage, filteredClientes.size)
    val currentClientes = filteredClientes.subList(startIndex, endIndex)

    Scaffold(
        topBar = { TopBarCustom(onNavigateToAgregarCliente) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE5E5E5))
        )
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Barra superior gris
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.Gray)
            )
            // Campo de búsqueda
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = {
                        viewModel.onChangeSearchText(it)
                        // Resetear a primera sub-página cuando se busca
                        currentSubPage = 1
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontFamily = Spooftrial_regular
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "Buscar con clave de cliente",
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        fontFamily = Spooftrial_regular
                                    )
                                }
                                innerTextField()
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.buscar),
                                contentDescription = "Buscar",
                                modifier = Modifier.size(20.dp),
                                tint = Color.DarkGray
                            )
                        }
                    }
                )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.Gray)
            )

            // Lista de clientes
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                if (currentClientes.isEmpty()) {
                    // Mensaje cuando no hay clientes
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (searchText.isEmpty())
                                "No hay clientes disponibles"
                            else
                                "No se encontraron clientes que coincidan con \"$searchText\"",
                            fontFamily = Spooftrial_regular,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        currentClientes.forEach { cliente ->
                            ClienteCard(
                                cliente = cliente,
                                onDeleteClick = {
                                    onNavigateToEliminarCliente(cliente)
                                },
                                onEditClick = {
                                    onNavigateToEditarCliente(cliente)
                                }
                            )
                        }
                    }
                }
            }
            ControlesPaginas(
                currentPage = currentPage,
                pageInputText = pageInputText,
                currentSubPage = currentSubPage,
                onChangePageInputText = { it -> pageInputText = it},
                onChangeCurrentPage = { it ->
                    currentPage = it
                    viewModel.getClientesPage(currentPage)
                },
                onChangeCurrentSubPage = { it -> currentSubPage = it},
                filteredClientes = filteredClientes,
                clientesPerSubPage = clientesPerSubPage
            )

        }
    }
}

