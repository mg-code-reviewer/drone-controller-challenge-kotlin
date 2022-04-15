package com.sumup.drone_challenge.handler

import com.sumup.drone_challenge.api.common.ErrorCauses
import com.sumup.drone_challenge.logic.ResponseError
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class ControllerExceptionHandler : BaseExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleDroneException(ex: Exception, request: WebRequest?): ResponseEntity<ResponseError> =
        ResponseEntity(generateResponseError(ex, ErrorCauses.ERROR_IN_REQUEST), HttpStatus.CONFLICT)
}
