package com.zero.conectacuatro.utils

import com.zero.conectacuatro.domain.model.Dot

fun defaultDots(): MutableList<Dot> {
    val dots = mutableListOf<Dot>()

    repeat(7) { col ->
        repeat(6) { row ->
            dots.add(Dot(false, col, row, 0))
        }
    }

    return dots
}