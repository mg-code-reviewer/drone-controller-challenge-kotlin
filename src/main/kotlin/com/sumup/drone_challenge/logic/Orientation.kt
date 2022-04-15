package com.sumup.drone_challenge.logic

import java.util.function.Consumer

class Orientation(minX: Int, maxX: Int, minY: Int, maxY: Int) {
    var direction: IDirection? = null
    var position: Position? = null

    @Transient
    var north: North? = null
        private set

    @Transient
    var east: East? = null
        private set

    @Transient
    var south: South? = null
        private set

    @Transient
    var west: West? = null
        private set

    init {
        val directions: MutableList<IDirection> = ArrayList()
        directions.add(setNorth(North(this, minX, maxX, minY, maxY)))
        directions.add(setEast(East(this, minX, maxX, minY, maxY)))
        directions.add(setSouth(South(this, minX, maxX, minY, maxY)))
        directions.add(setWest(West(this, minX, maxX, minY, maxY)))
        directions.forEach(Consumer { obj: IDirection -> obj.init() })
    }

    private fun setNorth(north: North): North {
        this.north = north
        return north
    }

    private fun setEast(east: East): East {
        this.east = east
        return east
    }

    private fun setSouth(south: South): South {
        this.south = south
        return south
    }

    private fun setWest(west: West): West {
        this.west = west
        return west
    }

    class DroneStateBuilder {
        private var direction: IDirection? = null
        private var position: Position? = null
        fun direction(direction: IDirection): DroneStateBuilder {
            this.direction = direction
            return this
        }

        fun position(position: Position): DroneStateBuilder {
            this.position = position
            return this
        }

        fun build(): Orientation {
            val orientation = Orientation(0, 9, 0, 9)
            orientation.direction = direction
            orientation.position = position
            return orientation
        }
    }

    companion object {
        fun builder(): DroneStateBuilder {
            return DroneStateBuilder()
        }
    }
}
