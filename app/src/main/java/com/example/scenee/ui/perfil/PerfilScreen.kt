package com.example.scenee.ui.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scenee.data.MockData
import com.example.scenee.ui.componentes.BottomBar
import com.example.scenee.ui.componentes.MovieCard

@Composable
fun PerfilScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = "perfil",
                onNavigate = onNavigate
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(80.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Nome", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Biografia", fontSize = 16.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Favoritos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(MockData.popularEstaSemana) { movie ->
                        MovieCard(movie = movie) {
                            onNavigate("detalhes/${movie.id}")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Comunidades",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(MockData.filmesDiretores) { movie ->
                        // Usando um MovieCard menor para a seção de comunidades, como na imagem
                        Box(modifier = Modifier.size(width = 140.dp, height = 120.dp)) {
                            MovieCard(movie = movie) {
                                onNavigate("detalhes/${movie.id}")
                            }
                        }
                    }
                }
            }
        }
    }
}