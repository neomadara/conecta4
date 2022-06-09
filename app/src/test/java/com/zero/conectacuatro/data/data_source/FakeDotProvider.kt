package com.zero.conectacuatro.data.data_source

import com.zero.conectacuatro.domain.model.Dot
import org.junit.Assert.*

class FakeDotProvider: InterfaceProvider {
    companion object {
        var dots:MutableList<Dot> = mutableListOf()
    }

    init {
        repeat(7) { col ->
            repeat(6) { row ->
                dots.add(Dot(false, col, row, 1))
            }
        }
    }

    override fun getDots(): MutableList<Dot> {
        return dots
    }

    override fun setDots(dotList: MutableList<Dot>) {
        dots = dotList
    }
}