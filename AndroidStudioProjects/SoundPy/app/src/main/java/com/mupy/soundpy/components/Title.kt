package com.mupy.soundpy.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mupy.soundpy.ui.theme.ColorWhite

@Composable
fun Title(
    text: String,
    color: Color = ColorWhite,
    fontSize: Int = 24,
    fontWeight: FontWeight = FontWeight.Bold,
    padding: Int = 32
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(padding.dp)
    )
    Text(
        text = text,
        color = color,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
    )
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(padding.dp)
    )
}