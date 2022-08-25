package com.zero.conectacuatro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.model.GameStatus
import com.zero.conectacuatro.ui.theme.ConectaCuatroTheme
import com.zero.conectacuatro.ui.view.Board
import com.zero.conectacuatro.ui.view.GamerMarker
import com.zero.conectacuatro.ui.viewmodel.BoardViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: BoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onCreated()

        setContent {
            ConectaCuatroTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Game(viewModel)
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
        Board(dots = emptyList(), onTap = {})
        Text(text = "Jugador 1 = 0")
        Text(text = "Jugador 2 = 0")
    }
}

@Composable
fun Game(viewModel: BoardViewModel) {
    val dots: List<Dot> by viewModel.dots
    val player: Number by viewModel.player
    val gameStatus: GameStatus by viewModel.gameStatus

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Board(
            dots = dots,
            onTap = { viewModel.selectDot(it) }
        )
        GamerMarker(
            actualPlayer = player,
            winsPlayerOne = gameStatus.winsPlayerOne,
            winsPlayerTwo = gameStatus.winsPlayerTwo)
    }
}