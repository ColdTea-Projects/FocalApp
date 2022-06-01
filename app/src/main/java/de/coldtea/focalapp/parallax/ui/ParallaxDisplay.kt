package de.coldtea.focalapp.parallax.ui

import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun ParallaxDisplay(parallaxViewModel: ParallaxViewModel){
    val data = parallaxViewModel.sensorData.collectAsState()

    Text(text = data.value.toString())
}