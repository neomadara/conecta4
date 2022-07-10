package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.utils.defaultDots

@Composable
fun Board(dots: List<Dot>, onTap: (Dot) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        repeat(7) { colIndex ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(6) { rowIndex ->
                    dots.find { it.row == rowIndex && it.column == colIndex }
                        ?.let { Circle(dot = it, onTap = onTap) }
                }
            }
        }
    }
}

@Preview("Board")
@Composable
fun DefaultPreview() {
    val dots = defaultDots()
    Board(dots = dots, onTap = {})
}

@Composable
fun Circle(dot: Dot, onTap: (Dot) -> Unit) {
    println(dot)
    Canvas(
        modifier = Modifier
            .size(50.dp)
            .clickable { onTap(dot) }
            .testTag("MyDotTag")
        ,
        onDraw = {
            drawCircle(
                color = handleDotColor(dot),
            )
        })
}

private fun handleDotColor(dot: Dot): Color {
    if (!dot.isActive) return Color.White
    val color = when (dot.playerId) {
        0 -> Color.White
        1 -> Color.Blue
        2 -> Color.Green
        else -> Color.White
    }
    return color
}

@Preview("Dot")
@Composable
fun CirclePreview() {
    Circle(dot = Dot(true, 0, 0, 1), onTap = {})
}