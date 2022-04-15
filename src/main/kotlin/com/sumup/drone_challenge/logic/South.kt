package com.sumup.drone_challenge.logic

class South(orientation: Orientation?, minX: Int, maxX: Int, minY: Int, maxY: Int) : Direction() {
    override val name = "SOUTH"

    init {
        this.orientation = orientation
        this.minX = minX
        this.maxX = maxX
        this.minY = minY
        this.maxY = maxY
    }

    override fun init() {
        left = orientation!!.east
        right = orientation!!.west
    }

    override fun moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                check(orientation!!.position!!.y - 1 >= minY) { "Cannot move" }
                orientation!!.position!!.y -= 1
            } else {
                throw IllegalStateException("Drone state is unknown")
            }
        } else {
            throw IllegalStateException("Directions are not set")
        }
    }
}