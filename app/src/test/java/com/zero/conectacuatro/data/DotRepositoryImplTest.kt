package com.zero.conectacuatro.data

import com.google.common.truth.Truth.assertThat
import com.zero.conectacuatro.data.data_source.FakeDotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.model.Player
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
    fun `Should active the last dot of the column even if the user send the first dot` () {
        val defaultDotList = defaultDotList()
        val dot = Dot(true, 0, 4,1)

        defaultDotList.find { it.row == 5 && it.column == 0 }?.isActive = true
        defaultDotList.find { it.row == 5 && it.column == 0 }?.playerId = 1

        repository.setDot(dot)
        val newDotList = repository.getDots()
        println(newDotList)
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

    @Test
    fun `Should search the quantity of dots in line of a player in a column and return the result` () {
        val dotOne = Dot(true, 0, 0,1)
        val dotTwo = Dot(true, 0, 0,1)
        val dotThree = Dot(true, 0, 0,1)

        repository.setDot(dotOne)
        repository.setDot(dotTwo)
        repository.setDot(dotThree)

        val resultExpected = Player(1, 3)
        val resultRepository = repository.searchWinner(1)

        assertThat(resultRepository).isEqualTo(resultExpected)
    }

    @Test
    fun `Should search the quantity of dots in line of a player in a row and return the result` () {
        val dotOne = Dot(true, 0, 0,1)
        val dotTwo = Dot(true, 1, 0,1)
        val dotThree = Dot(true, 2, 0,1)

        repository.setDot(dotOne)
        repository.setDot(dotTwo)
        repository.setDot(dotThree)


        val resultExpected = Player(1, 3)
        val resultRepository = repository.searchWinner(1)

        assertThat(resultRepository).isEqualTo(resultExpected)
    }

    @Test
    fun `Should search the quantity of dots in line of a player in a diagonal and return the result` () {
        val dotOne = Dot(true, 0, 0,1)
        val dotTwo = Dot(true, 1, 0,2)
        val dotThree = Dot(true, 1, 0,1)
        val dotFour = Dot(true, 2, 0,2)
        val dotFive = Dot(true, 1, 0,1)
        val dotSix = Dot(true, 2, 0,2)
        val dotSeven = Dot(true, 2, 0,1)

        repository.setDot(dotOne)
        repository.setDot(dotTwo)
        repository.setDot(dotThree)
        repository.setDot(dotFour)
        repository.setDot(dotFive)
        repository.setDot(dotSix)
        repository.setDot(dotSeven)

        val resultExpected = Player(1, 3)
        val resultRepository = repository.searchWinner(1)

        assertThat(resultRepository).isEqualTo(resultExpected)
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