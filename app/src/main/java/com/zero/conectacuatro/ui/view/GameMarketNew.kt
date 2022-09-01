package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.zero.conectacuatro.domain.model.GameStatus

@Composable
fun GamerMarkerNew(actualPlayer: Number, gameStatus: GameStatus, onResetCurrentGame: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Jugador 1 = ${gameStatus.winsPlayerOne}",
            Modifier.testTag("GameStatusTagPlayerOne")
        )
        Text(
            text = "Jugador 2 = ${gameStatus.winsPlayerTwo}",
            Modifier.testTag("GameStatusTagPlayerTwo")
        )
        Text(
            text = "ultimo jugador en seleccionar ${actualPlayer}"
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { onResetCurrentGame() }, modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)) {
                Text(text = "Reset Game")
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { onResetCurrentGame() }, modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)) {
                Text(text = "Clean Marker")
            }
        }
    }
}