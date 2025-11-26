package com.example.scenee

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.scenee.navigation.AppNavGraph

private val DarkColorPalette = darkColorScheme(
    background = Color(0xFF201A2A),
    surface = Color(0xFF2B223F),
    onBackground = Color.White,
    onSurface = Color.White,
    primary = Color.White
)

@Composable
fun SceneeApp() {
    MaterialTheme(
        colorScheme = DarkColorPalette
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavGraph()
        }
    }
}