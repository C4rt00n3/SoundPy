package com.mupy.soundpy.components.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mupy.soundpy.ui.theme.ColorWhite
import com.mupy.soundpy.ui.theme.TextColor2

@Composable
fun Option(
    load: Boolean = false,
    onClick: (() -> Unit) -> Unit,
    text: String,
    contentDescription: String,
    painter: Painter,
    expand: @Composable (() -> Unit)?,
) {
    var enabled by remember {
        mutableStateOf(true) // Initial state is enabled
    }

    Button(
        onClick = {
            if (enabled) {
                if (load) enabled = false
                onClick { if (load) enabled = true }
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, disabledContainerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        enabled = enabled // Use enabled state for button functionality
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (enabled) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(30.dp)
                )
            } else {
                CircularProgressIndicator(modifier = Modifier.size(30.dp), trackColor = TextColor2)
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = if(enabled) text else "Carregando... ", color = ColorWhite, fontWeight = FontWeight.Medium)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Column(modifier = Modifier.padding(horizontal = 9.dp)) {
        expand?.let { it() }
    }
}