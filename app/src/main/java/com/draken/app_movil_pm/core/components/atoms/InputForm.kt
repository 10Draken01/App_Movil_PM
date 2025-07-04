package com.draken.app_movil_pm.core.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draken.app_movil_pm.ui.theme.Spooftrial_regular


@Composable
fun InputForm(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    enable: Boolean? = null
) {

    Text(
        text = label,
        fontSize = 15.sp,
        color = Color.Black,
        fontFamily = Spooftrial_regular,
    )

    Spacer(modifier = Modifier.height(5.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = if(enable?: true) Color.White else Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
    ) {
        BasicTextField(
            enabled = enable?: true,
            value = value,
            onValueChange = onValueChange,
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
                }
            }
        )
    }
}