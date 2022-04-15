package com.sumup.drone_challenge.logic

open class Direction : IDirection {
    protected var orientation: Orientation? = null
    protected var left: IDirection? = null
    protected var right: IDirection? = null
    protected var minX = 0
    protected var maxX = 0
    protected var minY = 0
    protected var maxY = 0
    override val name: String
        get() = "UNDEFINED"

    override fun turnLeft() {
        if (left != null && right != null) {
            orientation?.direction = left
        } else {
            throw IllegalStateException("Directions are not set")
        }
    }

    override fun turnRight() {
        if (left != null && right != null) {
            orientation?.direction = right
        } else {
            throw IllegalStateException("Directions are not set")
        }
    }

    override fun moveForward() {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun init() {
        throw UnsupportedOperationException("Not implemented")
    }
}