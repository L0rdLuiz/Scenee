package com.example.scenee.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scenee.data.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(width = 140.dp, height = 200.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = movie.imageRes),
            contentDescription = "Capa do Filme",
            contentScale = ContentScale.Crop, // Crop vai cortar a imagem para preencher sem distorcer
            modifier = Modifier.fillMaxSize() // Esta linha faz a imagem preencher o Card
        )
    }
}