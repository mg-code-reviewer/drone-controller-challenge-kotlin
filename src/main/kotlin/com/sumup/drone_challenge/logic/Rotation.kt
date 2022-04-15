package com.sumup.drone_challenge.logic

import java.util.Objects
import javax.validation.constraints.Pattern

class Rotation {
    @Pattern(
        regexp = "^[LR]{1}$",
        message = "The rotation string used is not valid. The rotation value must be L or R"
    )
    var rotation: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val rotation1 = other as Rotation
        return rotation == rotation1.rotation
    }

    override fun hashCode(): Int {
        return Objects.hash(rotation)
    }

    override fun toString(): String {
        return "Rotation{" +
                "rotation='" + rotation + '\'' +
                '}'
    }
}