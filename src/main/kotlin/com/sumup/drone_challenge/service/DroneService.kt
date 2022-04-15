package com.sumup.drone_challenge.service

import com.sumup.drone_challenge.logic.Drone
import com.sumup.drone_challenge.logic.Orientation
import com.sumup.drone_challenge.logic.Position
import com.sumup.drone_challenge.logic.Sector
import java.util.Random
import org.springframework.stereotype.Service

@Service
class DroneService {
    private val sector = Sector(10, 10)
    val drone: Drone

    init {
        //Randomly choose the position and direction of the drone on the sector
        val rand = Random()
        val randomX = rand.nextInt(1 + 10)
        val randomY = rand.nextInt(1 + 10)
        val randomDirection = rand.nextInt(1 + 2)
        drone = Drone(sector, randomX, randomY, Drone.directions.get(randomDirection))
    }

    val orientation: Orientation
        get() = Orientation.builder()
            .direction(drone.droneState.direction!!)
            .position(drone.droneState.position!!)
            .build()

    fun where(): Position {
        val position = Position()
        position.x = drone.x
        position.y = drone.y
        return position
    }
}