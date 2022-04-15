package com.sumup.drone_challenge.logic

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SectorTest {
    @Test
    fun `test sector instantiation`() {
        val maximumX = 10
        val maximumY = 10
        val sector = Sector(maximumX, maximumY)
        Assertions.assertEquals(maximumX, sector.maximumX)
        Assertions.assertEquals(maximumY, sector.maximumY)
    }
}
