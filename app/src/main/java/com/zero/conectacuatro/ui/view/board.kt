package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Circle(
    val active: Boolean = false
)

@Composable
fun Board() {
    Row(
        modifier = Modifier.fillMaxWidth(),
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

@Preview
@Composable
fun DefaultPreview() {
    Board()
}

@Preview("Circle")
@Composable
fun Circle() {
    OutlinedButton(onClick = { /*TODO*/ },
        modifier= Modifier.size(50.dp),  //avoid the oval shape
        shape = CircleShape,
        border= BorderStroke(1.dp, Color.Blue),
        contentPadding = PaddingValues(0.dp),  //avoid the little icon
        colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color.Blue)
    ) {}
}