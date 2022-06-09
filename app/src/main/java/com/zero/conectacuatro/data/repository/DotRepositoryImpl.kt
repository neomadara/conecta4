package com.zero.conectacuatro.data.repository

import com.zero.conectacuatro.data.data_source.InterfaceProvider
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.repository.DotRepository
import kotlin.math.log

class DotRepositoryImpl(
    private val sourceData: InterfaceProvider): DotRepository {

    override fun getDots(): MutableList<Dot> {
        return sourceData.getDots()
    }

    override fun setDot(dot: Dot) {
        val dotList = sourceData.getDots()
        val dotIndex = dotList.indexOfFirst { it.row == dot.row && it.column == dot.column }
        val dotState: Dot = dotList.single { d -> d.column == dot.column && d.row == dot.row }
        dotState.isActive = !dotState.isActive
        dotList[dotIndex] = dotState
    }
}