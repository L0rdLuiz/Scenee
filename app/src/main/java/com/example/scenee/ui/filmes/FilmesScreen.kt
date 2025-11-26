package com.example.scenee.ui.filmes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scenee.data.MockData
import com.example.scenee.ui.componentes.BottomBar
import com.example.scenee.ui.componentes.MovieCard

@Composable
fun FilmesScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Scenee", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Filmes", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Listas", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                }
            }
        },
        bottomBar = {
            BottomBar(currentRoute = "filmes", onNavigate = onNavigate)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text("Popular esta semana", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(MockData.popularEstaSemana) { movie ->
                    MovieCard(movie = movie) {
                        onNavigate("detalhes/${movie.id}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Filmes de diretores que você curte", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(MockData.filmesDiretores) { movie ->
                    MovieCard(movie = movie) {
                        onNavigate("detalhes/${movie.id}")
                    }
                }
            }
        }
    }
}