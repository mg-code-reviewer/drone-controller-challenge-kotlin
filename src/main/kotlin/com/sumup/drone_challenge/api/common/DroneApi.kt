package com.sumup.drone_challenge.api.common

import com.sumup.drone_challenge.logic.Orientation
import com.sumup.drone_challenge.logic.Position
import com.sumup.drone_challenge.logic.Response
import com.sumup.drone_challenge.logic.Rotation
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/v1/drone")
@Tag(name = "drone", description = "Drone's api")
interface DroneApi {
    @Operation(
        summary = "Rotate the drone in two directions: R (right) and L (left)",
        description = "Rotate the drone in two directions: R (right) and L (left)",
        tags = ["drone"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        )]
    )
    @PutMapping(value = ["/rotate"])
    fun rotate(rotation: Rotation): Response<Orientation>

    @Operation(
        summary = "Move the drone forward in the direction that is pointing",
        description = "Move the drone forward in the direction that is pointing",
        tags = ["drone"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        )]
    )
    @PutMapping(value = ["/forward"])
    fun goForward(): Response<Orientation>

    @Operation(
        summary = "Give the position where the drone is located",
        description = "Give the position where the drone is located",
        tags = ["drone"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        ), ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)]
        )]
    )
    @GetMapping(value = ["/where"])
    fun where(): Response<Position>
}
