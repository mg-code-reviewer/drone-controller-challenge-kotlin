package com.sumup.drone_challenge.api.controller

import com.sumup.drone_challenge.api.common.DroneApi
import com.sumup.drone_challenge.logic.Drone
import com.sumup.drone_challenge.logic.Orientation
import com.sumup.drone_challenge.logic.Position
import com.sumup.drone_challenge.logic.Response
import com.sumup.drone_challenge.logic.Rotation
import com.sumup.drone_challenge.logic.Validator
import com.sumup.drone_challenge.service.DroneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DroneController : DroneApi {
    @Autowired
    private lateinit var droneService: DroneService

    @Autowired
    private lateinit var validator: Validator

    override fun rotate(@RequestBody rotation: Rotation): Response<Orientation> {
        validator.validate(rotation)
        runCommand(droneService.drone, rotation.rotation!!)
        return Response(droneService.orientation)
    }

    override fun goForward(): Response<Orientation> {
        runCommand(droneService.drone, "F")
        return Response(droneService.orientation)
    }

    override fun where(): Response<Position> {
        return Response(droneService.where())
    }

    fun runCommand(drone: Drone, direction: String) {
        when (direction) {
            "R" -> drone.droneState.direction!!.turnRight()
            "L" -> drone.droneState.direction!!.turnLeft()
            "F" -> drone.droneState.direction!!.moveForward()
            else -> println("Error happened")
        }
    }
}