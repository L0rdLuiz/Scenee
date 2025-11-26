package com.example.scenee.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Person
import androidx.compose.foundation.layout.*
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "filmes",
            onClick = { onNavigate("filmes") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == "buscar",
            onClick = { onNavigate("buscar") },
            icon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            label = { Text("Buscar") }
        )

        NavigationBarItem(
            selected = currentRoute == "perfil",
            onClick = { onNavigate("perfil") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )
    }
}