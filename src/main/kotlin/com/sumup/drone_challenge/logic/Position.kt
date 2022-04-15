package com.sumup.drone_challenge.logic

import java.util.Objects

class Position {
    var x = 0
    var y = 0
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val position = o as Position
        return x == position.x && y == position.y
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }

    override fun toString(): String {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}'
    }
}