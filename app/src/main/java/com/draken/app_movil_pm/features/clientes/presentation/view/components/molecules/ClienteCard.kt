package com.draken.app_movil_pm.features.clientes.presentation.view.components.molecules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.core.domain.model.Cliente

// Constantes para mantener consistencia en el diseño
private val CARD_PADDING = 6.dp
private val CARD_ELEVATION = 2.dp
private val CARD_CORNER_RADIUS = 8.dp
private const val ANIMATION_DURATION = 300
@Composable
fun ClienteCard(
    cliente: Cliente,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(CARD_PADDING)
            .clickable(
                onClickLabel = if (isExpanded) "Contraer información" else "Expandir información"
            ) {
                isExpanded = !isExpanded
            }
            .semantics {
                contentDescription = "Tarjeta del cliente ${cliente.nombre}. " +
                        if (isExpanded) "Expandida" else "Contraída"
            }
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(CARD_CORNER_RADIUS)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = CARD_ELEVATION
        ),
        shape = RoundedCornerShape(CARD_CORNER_RADIUS)
    ) {
        Column {
            // Header de la tarjeta
            ClienteCardHeader(
                cliente = cliente,
                isExpanded = isExpanded,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )

            // Contenido expandible con animación
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(
                    animationSpec = tween(ANIMATION_DURATION)
                ) + fadeIn(
                    animationSpec = tween(ANIMATION_DURATION)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(ANIMATION_DURATION)
                ) + fadeOut(
                    animationSpec = tween(ANIMATION_DURATION)
                )
            ) {
                ClienteExpandedContent(cliente = cliente)
            }
        }
    }
}
