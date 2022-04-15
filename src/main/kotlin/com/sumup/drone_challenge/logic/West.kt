package com.sumup.drone_challenge.logic

class West(orientation: Orientation?, minX: Int, maxX: Int, minY: Int, maxY: Int) : Direction() {
    override val name = "WEST"

    init {
        this.orientation = orientation
        this.minX = minX
        this.maxX = maxX
        this.minY = minY
        this.maxY = maxY
    }

    override fun init() {
        left = orientation!!.south
        right = orientation!!.north
    }

    override fun moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                check(orientation!!.position!!.x - 1 >= minX) { "Cannot move" }
                orientation!!.position!!.x -= 1
            } else {
                throw IllegalStateException("Drone state is unknown")
            }
        } else {
            throw IllegalStateException("Directions are not set")
        }
    }
}