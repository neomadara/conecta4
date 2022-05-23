package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Dot(
    val _id: String = "",
    val isActive: Boolean = false
)

@Composable
fun Board() {
    val dot = Dot(isActive = false)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        repeat(7) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(6) {
                    Circle(dot)
                }
            }
        }
    }
}

@Preview("Board")
@Composable
fun DefaultPreview() {
    Board()
}

@Composable
fun Circle(Dot: Dot) {
    val activeState = remember { mutableStateOf(Dot.isActive) }
    Canvas(modifier = Modifier.size(50.dp), onDraw = {
        drawCircle(
            color = if (activeState.value) Color.Blue else Color.White,
        )
    })
}