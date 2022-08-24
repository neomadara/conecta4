package com.zero.conectacuatro.data.repository

import com.zero.conectacuatro.data.data_source.InterfaceProvider
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.model.Player
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

    override fun searchWinner(player: Number): Player {
        val dots = sourceData.getDots().toList()
        var tempCount = 0
        var dotsInLine = 0

        for (col in 0..6) {
            for (row in 5 downTo 0) {
                val dot = dots.find { it.column == col && it.row == row }
                if (dot?.playerId == player && dot.isActive) {
                    // count in line dot in Column
                    for (x in row downTo 0) {
                        val dotColumn = dots.find { it.column == col && it.row == x}
                        if (dotColumn?.playerId == player && dotColumn.isActive) {
                          tempCount += 1
                        } else {
                            break
                        }
                    }

                    if (tempCount > dotsInLine) {
                        dotsInLine = tempCount
                    }
                    tempCount = 0

                    // count in line dot in Row
                    for (y in col..6) {
                        val dotRow = dots.find { it.column == y && it.row == row }
                        if (dotRow?.playerId == player && dotRow.isActive) {
                            tempCount += 1
                        } else {
                            break
                        }
                    }

                    if (tempCount > dotsInLine) {
                        dotsInLine = tempCount
                    }
                    tempCount = 0

                    // count in line dot in Diagonal
                    for (dotPunter in 0..3) {
                        val dotCol = dot.column + dotPunter
                        val dotRow = dot.row - dotPunter

                        val dotDiagonal = dots.find { it.column == dotCol && it.row == dotRow}
                        if (dotDiagonal?.playerId == player && dotDiagonal.isActive) {
                            tempCount += 1
                        } else {
                            break
                        }
                    }


                    if (tempCount > dotsInLine) {
                        dotsInLine = tempCount
                    }
                    tempCount = 0

                }

            }
        }

        return Player(player, dotsInLine)
    }

    private fun determinateColumn(dots: MutableList<Dot>,dot: Dot): MutableList<Dot> {
        return dots.filter { it.column == dot.column }.toMutableList()
    }
}