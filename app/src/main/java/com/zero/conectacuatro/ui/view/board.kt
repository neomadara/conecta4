package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Board() {
    Row(
        modifier = Modifier.fillMaxWidth().background(color = Color.Gray).padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        repeat(7) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(6) {
                    Circle()
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

@Preview("Circle")
@Composable
fun Circle() {
    Canvas(modifier = Modifier.size(50.dp), onDraw = {
        drawCircle(
            color = Color.White,
        )
    })
}