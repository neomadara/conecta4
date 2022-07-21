package com.zero.conectacuatro.domain.repository

import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.model.Player

interface DotRepository {
    fun getDots(): List<Dot>

    fun setDot(dot: Dot)

    fun resetDots()

    fun searchWinner(player: Number): Player
}