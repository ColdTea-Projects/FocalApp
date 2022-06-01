package de.coldtea.focalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import de.coldtea.focalapp.parallax.ui.ParallaxDisplay
import de.coldtea.focalapp.parallax.ui.ParallaxViewModel
import de.coldtea.focalapp.shared.ui.theme.FocalAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FocalAppTheme {
                val parallaxViewModel = hiltViewModel<ParallaxViewModel>()

                ParallaxDisplay(parallaxViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FocalAppTheme {
        Greeting("Android")
    }
}