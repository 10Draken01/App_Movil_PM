package com.draken.app_movil_pm.features.clientes.presentation.view.components.atoms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.draken.app_movil_pm.core.domain.model.CharacterIcon

@Composable
fun CharacterIcon(characterIcon: CharacterIcon) {
    Surface(
        shape = CircleShape,
        color = Color.Black,
        modifier = Modifier.size(50.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = characterIcon.characterIconNumber),
                contentDescription = "Profile",
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}