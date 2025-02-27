package com.ashish.dogimagegeneratorwithlrucache.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ashish.dogimagegeneratorwithlrucache.ui.theme.BlueButtonColor

@Composable
fun OutlinedBlueButton(
    text: String,
    onClick: () -> Unit,
    color: Color = BlueButtonColor,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        border = BorderStroke(1.dp, Color.Black), // Black outline
        modifier = modifier
    ) {
        Text(text, color = Color.White)
    }
}
