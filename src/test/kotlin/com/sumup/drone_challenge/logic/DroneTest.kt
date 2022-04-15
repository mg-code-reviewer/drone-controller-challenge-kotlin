package com.sumup.drone_challenge.logic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DroneTest {
    @Test
    fun `test drone sector constructor`() {
        val maximumX = 10
        val maximumY = 10
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector)
        assertEquals(0, drone.x)
        assertEquals(0, drone.y)
        assertEquals("NORTH", drone.getDirection())
        assertEquals(Sector(maximumX, maximumY), drone.sector)
    }

    @Test
    fun `test drone sector X and Y direction constructor`() {
        val maximumX = 10
        val maximumY = 10
        val x = 1
        val y = 1
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector, x, y, "S")
        assertEquals(x, drone.x)
        assertEquals(y, drone.y)
        assertEquals("SOUTH", drone.getDirection())
        assertEquals(Sector(maximumX, maximumY), drone.sector)
    }

    @Test
    fun `test drone sector X Y and other direction constructor`() {
        val maximumX = 10
        val maximumY = 10
        val x = 1
        val y = 1
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector, x, y, "E")
        assertEquals(x, drone.x)
        assertEquals(y, drone.y)
    }

    @ParameterizedTest
    @CsvSource(
        "3, 3, 0, 0, N, L, WEST, 0, 0",
        "3, 3, 0, 0, S, R, WEST, 0, 0",
        "3, 3, 0, 0, E, L, NORTH, 0, 0",
        "3, 3, 0, 0, E, R, SOUTH, 0, 0",
        "3, 3, 0, 0, W, L, SOUTH, 0, 0",
        "3, 3, 0, 0, W, R, NORTH, 0, 0",
        "3, 3, 2, 2, N, F, NORTH, 2, 3",
        "3, 3, 2, 2, W, F, WEST, 1, 2",
        "3, 3, 2, 2, S, F, SOUTH, 2, 1"
    )
    fun `test run command parameterized`(
        maximumX: Int,
        maximumY: Int,
        x: Int,
        y: Int,
        directionStr: String?,
        command: String?,
        expectedDirection: String?,
        expectedX: Int,
        expectedY: Int
    ) {
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector, x, y, directionStr)
        runCommand(drone, command)
        assertEquals(expectedDirection, drone.getDirection())
        assertEquals(expectedX, drone.x)
        assertEquals(expectedY, drone.y)
    }

    private fun runCommand(drone: Drone, direction: String?) =
        when (direction) {
            "R" -> drone.droneState.direction!!.turnRight()
            "L" -> drone.droneState.direction!!.turnLeft()
            "F" -> drone.droneState.direction!!.moveForward()
            else -> {}
        }
}
