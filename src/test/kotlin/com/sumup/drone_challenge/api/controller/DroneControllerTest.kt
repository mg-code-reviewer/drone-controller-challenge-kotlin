package com.sumup.drone_challenge.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.sumup.drone_challenge.api.common.ErrorCauses
import com.sumup.drone_challenge.logic.Direction
import com.sumup.drone_challenge.logic.Drone
import com.sumup.drone_challenge.logic.Error
import com.sumup.drone_challenge.logic.Orientation
import com.sumup.drone_challenge.logic.Position
import com.sumup.drone_challenge.logic.Response
import com.sumup.drone_challenge.logic.ResponseError
import com.sumup.drone_challenge.logic.Rotation
import com.sumup.drone_challenge.logic.Validator
import com.sumup.drone_challenge.service.DroneService
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Clock
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.validation.Validation

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [DroneController::class])
class DroneControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var droneService: DroneService

    @MockkBean
    lateinit var drone: Drone

    @MockkBean
    lateinit var orientation: Orientation

    @MockkBean
    lateinit var direction: Direction

    @MockkBean
    lateinit var validator: Validator

    @MockkBean
    lateinit var clock: Clock

    @Test
    fun `test drone rotate`() {
        val request = buildOrientationResponse()
        every { (validator).validate(any()) } returns Unit
        every { droneService.orientation } returns buildOrientation()
        every { (droneService.drone) } returns drone
        every { (drone.droneState) } returns orientation
        every { (orientation.direction) } returns direction
        every { (direction.name) } returns "TEST"
        every { (clock.millis()) } returns 1L
        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()

        //Executing the test
        val response = mockMvc.perform(
            MockMvcRequestBuilders.put(CONTEXT_URL + DRONE_ROTATION)
                .contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(request))
        )
            .andReturn()
            .response
            .contentAsString

        assertThat(response.isEmpty()).isFalse
    }

    @Test
    fun `test drone rotate http status 400`() {
        val rotation = Rotation()
        rotation.rotation = "Z"
        val responseError = buildResponseErrorForRotateDrone(rotation)
        every { (validator).validate(any()) } throws ConstraintViolationException(any())
        every { (droneService.orientation) } returns buildOrientation()
        every { (droneService.drone) } returns drone
        every { (drone.droneState) } returns orientation
        every { (orientation.direction) } returns direction
        every { (clock.millis()) } returns 1L
        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()

        //Executing the test
        mockMvc.perform(
            MockMvcRequestBuilders.put(CONTEXT_URL + DRONE_ROTATION)
                .contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(responseError))
        )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun `test drone move forward`() {
        val request = buildOrientationResponse()
        every { (validator).validate(any()) } returns Unit
        every { (droneService.orientation) } returns buildOrientation()
        every { (droneService.drone) } returns drone
        every { (drone.droneState) } returns orientation
        every { (orientation.direction) } returns direction
        every { (direction.moveForward()) } returns Unit
        every { (clock.millis()) } returns 1L
        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()

        //Executing the test
        mockMvc.perform(
            MockMvcRequestBuilders.put(CONTEXT_URL + DRONE_FORWARD)
                .contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testDroneWhere() {
        val request = buildPositionResponse()
        every { (droneService.where()) } returns buildPosition()
        every { (clock.millis()) } returns 1L
        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()

        //Executing the test
        mockMvc.perform(
            MockMvcRequestBuilders.get(CONTEXT_URL + DRONE_WHERE)
                .contextPath(CONTEXT_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectWriter.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun buildOrientation(): Orientation {
        val orientation = Orientation(0, 9, 0, 9)
        orientation.direction = orientation.north
        return orientation
    }

    private fun buildOrientationResponse(): Response<Orientation> {
        val orientation = buildOrientation()
        return Response(orientation)
    }

    private fun buildPosition(): Position {
        val position = Position()
        position.x = 2
        position.y = 2
        return position
    }

    private fun buildPositionResponse(): Response<Position> = Response(buildPosition())

    private fun buildViolation(obj: Any): ConstraintViolation<Any> {
        val violationSet =
            Validation.buildDefaultValidatorFactory().validator.validate(obj)
        return violationSet.toTypedArray().first()
    }

    @Suppress("UNCHECKED_CAST")
    private fun buildResponseErrorForRotateDrone(obj: Any): ResponseError {
        val violation: ConstraintViolation<Rotation> = buildViolation(obj) as ConstraintViolation<Rotation>
        val error = Error()
        error.code = ErrorCauses.ERROR_IN_REQUEST.id
        error.description = ErrorCauses.ERROR_IN_REQUEST.message
        error.detail = violation.message
        return ResponseError(error)
    }

    companion object {
        private const val CONTEXT_URL = "/api"
        private const val DRONE_ROTATION = "/v1/drone/rotate"
        private const val DRONE_FORWARD = "/v1/drone/forward"
        private const val DRONE_WHERE = "/v1/drone/where"
    }
}
