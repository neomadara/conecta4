package com.zero.conectacuatro.data

import com.google.common.truth.Truth.assertThat
import com.zero.conectacuatro.data.data_source.FakeDotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.repository.DotRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

class DotRepositoryImplTest {

    private lateinit var repository: DotRepository
    private lateinit var fakeProvider: FakeDotProvider

    @Before
    fun before() {
        fakeProvider = FakeDotProvider()
        repository = DotRepositoryImpl(fakeProvider)
    }

    @After
    fun after() {
        repository.resetDots()
    }

    @Test
    fun `Should return an init Dot list`() {
        val defaultList = defaultDotList()

        val initDotList = repository.getDots()

        assertThat(initDotList).isEqualTo(defaultList)
    }

    @Test
    fun `Should search if the dot selected is the first active in the column, if this is true the last dot is active` () {
        val defaultDotList = defaultDotList()
        val dot = Dot(true, 0, 0,1)

        defaultDotList.find { it.row == 5 && it.column == 0 }?.isActive = true
        defaultDotList.find { it.row == 5 && it.column == 0 }?.playerId = 1

        repository.setDot(dot)
        val newDotList = repository.getDots()
        assertThat(defaultDotList).isEqualTo(newDotList)
    }

    @Test
    fun `Should search if the dot selected is the first active in the column, if this false activate the next dot who is active` () {
        val dotsExpected = defaultDotList()

        val dotPlayerOne = Dot(true, 0, 0,1)
        val dotPlayerTwo = Dot(true, 0, 0,2)

        dotsExpected.find { it.row == 5 && it.column == 0 }?.isActive = true
        dotsExpected.find { it.row == 5 && it.column == 0 }?.playerId = 1

        dotsExpected.find { it.row == 4 && it.column == 0 }?.isActive = true
        dotsExpected.find { it.row == 4 && it.column == 0 }?.playerId = 2

        repository.setDot(dotPlayerOne)
        repository.setDot(dotPlayerTwo)

        val newDotList = repository.getDots()
        assertThat(dotsExpected).isEqualTo(newDotList)
    }
}

fun defaultDotList(): MutableList<Dot> {
    val dotsToInsert = mutableListOf<Dot>()
    repeat(7) { col ->
        repeat(6) { row ->
            dotsToInsert.add(Dot(false, col, row, 0))
        }
    }
    return dotsToInsert
}