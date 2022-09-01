package com.zero.conectacuatro.data.data_source

import com.zero.conectacuatro.domain.model.Dot

class DotProvider: InterfaceProvider {
    companion object {
        var dots:MutableList<Dot> = mutableListOf()
    }

    init {
        fillDots()
    }

    override fun getDots(): MutableList<Dot> {
        return dots
    }

    override fun setDots(dotList: MutableList<Dot>) {
        dots = dotList
    }

    override fun resetDots() {
        dots.clear()
        fillDots()
    }

    private fun fillDots() {
        repeat(7) { col ->
            repeat(6) { row ->
                dots.add(Dot(false, col, row, 0))
            }
        }
    }
}