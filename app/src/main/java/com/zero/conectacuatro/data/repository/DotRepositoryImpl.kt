package com.zero.conectacuatro.data.repository

import com.zero.conectacuatro.data.data_source.InterfaceProvider
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.repository.DotRepository

class DotRepositoryImpl(
    private val sourceData: InterfaceProvider): DotRepository {

    override fun getDots(): MutableList<Dot> {
        return sourceData.getDots()
    }

    override fun setDot(dot: Dot) {
        val dotList = sourceData.getDots()
        val columnDots = determinateColumn(dotList, dot)

        for (i in columnDots.indices.reversed()) {
            if (!columnDots[i].isActive) {
                columnDots[i].isActive = true
                columnDots[i].playerId = dot.playerId
                break
            }
        }

        for (idx in columnDots.indices) {
            dotList.find { columnDots[idx].column == it.column && columnDots[idx].row == it.row }?.isActive = columnDots[idx].isActive
            dotList.find { columnDots[idx].column == it.column && columnDots[idx].row == it.row }?.playerId = columnDots[idx].playerId
        }

        sourceData.setDots(dotList)
    }

    override fun resetDots() {
        sourceData.resetDots()
    }

    private fun determinateColumn(dots: MutableList<Dot>,dot: Dot): MutableList<Dot> {
        return dots.filter { it.column == dot.column }.toMutableList()
    }
}