package com.sumup.drone_challenge.logic

class North(orientation: Orientation?, minX: Int, maxX: Int, minY: Int, maxY: Int) : Direction() {
    override val name = "NORTH"

    init {
        this.orientation = orientation
        this.minX = minX
        this.maxX = maxX
        this.minY = minY
        this.maxY = maxY
    }

    override fun init() {
        left = orientation!!.west
        right = orientation!!.east
    }

    override fun moveForward() {
        if (left != null && right != null) {
            if (orientation != null) {
                check(orientation!!.position!!.y + 1 !in (maxY + 1) until minY) { "Cannot move" }
                orientation!!.position!!.y += 1
            } else {
                throw IllegalStateException("Drone state is unknown")
            }
        } else {
            throw IllegalStateException("Directions are not set")
        }
    }
}