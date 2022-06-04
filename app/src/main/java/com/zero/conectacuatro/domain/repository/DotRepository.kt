package com.zero.conectacuatro.domain.repository

import com.zero.conectacuatro.domain.model.Dot

interface DotRepository {
    fun getDots(): List<Dot>

    fun setDot(dot: Dot)

    fun setDots(dots: MutableList<Dot>)
}