package com.sumup.drone_challenge.logic

class East(orientation: Orientation?, minX: Int, maxX: Int, minY: Int, maxY: Int) : Direction() {
    override val name = "EATS"

    init {
        this.orientation = orientation
        this.minX = minX
        this.maxX = maxX
        this.minY = minY
        this.maxY = maxY
    }

    override fun init() {
        left = orientation!!.north
        right = orientation!!.south
    }

    override fun moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                check(orientation!!.position!!.x + 1 !in (maxX + 1) until minX) { "Cannot move" }
                orientation!!.position!!.x += 1
            } else {
                throw IllegalStateException("Drone state is unknown")
            }
        } else {
            throw IllegalStateException("Directions are not set")
        }
    }
}