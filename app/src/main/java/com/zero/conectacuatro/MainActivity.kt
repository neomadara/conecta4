package com.zero.conectacuatro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zero.conectacuatro.ui.theme.ConectaCuatroTheme
import com.zero.conectacuatro.ui.view.Board

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConectaCuatroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Board()
                        Text(text = "Jugador 1 = 0")
                        Text(text = "Jugador 2 = 0")
                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun BoardPreview() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Jugador 1 = 0")
        Text(text = "Jugador 2 = 0")
    }
}