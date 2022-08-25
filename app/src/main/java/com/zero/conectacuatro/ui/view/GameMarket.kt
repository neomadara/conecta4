package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun GamerMarker(actualPlayer: Number, winsPlayerOne: Number, winsPlayerTwo: Number) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Jugador 1 = $winsPlayerOne",
            Modifier.testTag("GameStatusTagPlayerOne")
        )
        Text(
            text = "Jugador 2 = $winsPlayerTwo",
            Modifier.testTag("GameStatusTagPlayerTwo")
        )
        Text(
            text = "ultimo jugador en seleccionar ${actualPlayer}"
        )
    }
}