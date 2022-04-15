package com.sumup.drone_challenge.logic

import java.util.Arrays
import java.util.Collections
import java.util.Objects

class Drone {
    var droneState: Orientation
        private set
    var sector: Sector
        private set
    var x = 0
        get() {
            x = droneState.position?.x ?: 0
            return field
        }
    var y = 0
        get() {
            y = droneState.position?.y ?: 0
            return field
        }
    private var direction = 0

    constructor(sector: Sector) {
        droneState = Orientation(0, 9, 0, 9)
        val position = Position()
        position.x = x
        position.y = y
        droneState.direction = droneState.north
        droneState.position = position
        this.sector = sector
    }

    constructor(sector: Sector, x: Int, y: Int, direction: String?) {
        droneState = Orientation(0, 9, 0, 9)
        val position = Position()
        position.x = x
        position.y = y
        droneState.direction = droneState.north
        droneState.position = position
        this.sector = sector
        this.x = x
        this.y = y
        this.direction =
            if (Arrays.binarySearch(DIRECTIONS, direction) > 0) Arrays.binarySearch(DIRECTIONS, direction) else 0
        when (direction) {
            "N" -> droneState.direction = droneState.north
            "E" -> droneState.direction = droneState.east
            "S" -> droneState.direction = droneState.south
            "W" -> droneState.direction = droneState.west
            else -> {}
        }
    }

    fun getDirection(): String {
        return droneState.direction!!.name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val drone = other as Drone
        return x == drone.x && y == drone.y && direction == drone.direction && sector == drone.sector
    }

    override fun hashCode(): Int {
        return Objects.hash(sector, x, y, direction)
    }

    override fun toString(): String {
        return "Drone{" +
                "sector=" + sector +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}'
    }

    companion object {
        private val DIRECTIONS = arrayOf("N", "E", "S", "W")
        val directions: List<String>
            get() = Collections.unmodifiableList(Arrays.asList(*DIRECTIONS))
    }
}