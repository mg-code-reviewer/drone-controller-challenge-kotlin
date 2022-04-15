package com.sumup.drone_challenge.logic

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DroneTest {
    @Test
    fun testDroneSectorConstructor() {
        val maximumX = 10
        val maximumY = 10
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector)
        Assertions.assertEquals(0, drone.x)
        Assertions.assertEquals(0, drone.y)
        Assertions.assertEquals("NORTH", drone.getDirection())
        Assertions.assertEquals(Sector(maximumX, maximumY), drone.sector)
    }

    @Test
    fun testDroneSectorXYDirectionConstructor() {
        val maximumX = 10
        val maximumY = 10
        val x = 1
        val y = 1
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector, x, y, "S")
        Assertions.assertEquals(x, drone.x)
        Assertions.assertEquals(y, drone.y)
        Assertions.assertEquals("SOUTH", drone.getDirection())
        Assertions.assertEquals(Sector(maximumX, maximumY), drone.sector)
    }

    @Test
    fun testDroneSectorXYOtherDirectionConstructor() {
        val maximumX = 10
        val maximumY = 10
        val x = 1
        val y = 1
        val sector = Sector(maximumX, maximumY)
        val drone = Drone(sector, x, y, "E")
        Assertions.assertEquals(x, drone.x)
        Assertions.assertEquals(y, drone.y)
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
    fun testRunCommandParameterized(
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
        Assertions.assertEquals(expectedDirection, drone.getDirection())
        Assertions.assertEquals(expectedX, drone.x)
        Assertions.assertEquals(expectedY, drone.y)
    }

    private fun runCommand(drone: Drone, direction: String?) {
        when (direction) {
            "R" -> drone.droneState.direction!!.turnRight()
            "L" -> drone.droneState.direction!!.turnLeft()
            "F" -> drone.droneState.direction!!.moveForward()
            else -> {}
        }
    }
}