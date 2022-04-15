package com.sumup.drone_challenge.logic

import java.util.Objects

class Sector(maximumX: Int, maximumY: Int) {
    var maximumX = 0
    var maximumY = 0

    init {
        this.maximumX = maximumX
        this.maximumY = maximumY
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val sector = o as Sector
        return maximumX == sector.maximumX && maximumY == sector.maximumY
    }

    override fun hashCode(): Int {
        return Objects.hash(maximumX, maximumY)
    }

    override fun toString(): String {
        return "Sector{" +
                "maximumX=" + maximumX +
                ", maximumY=" + maximumY +
                '}'
    }
}