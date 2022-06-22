package com.zero.conectacuatro.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zero.conectacuatro.domain.model.Dot

fun fillDots(): MutableList<Dot> {
    val dots = mutableListOf<Dot>()

    repeat(7) { col ->
        repeat(6) { row ->
            dots.add(Dot(false, col, row, 0))
        }
    }

    return dots
}


fun handleTap(dot: Dot) {
    println("$dot.col, $dot.row")
}

@Composable
fun Board() {
    val dots: List<Dot> = fillDots()

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
                        ?.let { Circle(dot = it) }
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
fun Circle(dot: Dot) {
    val activeState = remember { mutableStateOf(dot.isActive) }
    Canvas(
        modifier = Modifier
            .size(50.dp)
            .clickable { println(dot) }
            .testTag("MyDotTag")
        ,
        onDraw = {
            drawCircle(
                color = if (activeState.value) Color.Blue else Color.White,
            )
        })
}