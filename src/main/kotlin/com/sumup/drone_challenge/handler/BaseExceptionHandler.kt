package com.sumup.drone_challenge.handler

import com.sumup.drone_challenge.api.common.ErrorCauses
import com.sumup.drone_challenge.logic.Error
import com.sumup.drone_challenge.logic.ResponseError
import java.util.Arrays
import java.util.stream.Collectors
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

abstract class BaseExceptionHandler : ResponseEntityExceptionHandler() {
    @Value("\${openapi.show-stacktrace:true}")
    private val showStackTrace: Boolean? = null
    private fun getStackTrace(ex: Exception): List<String>? {
        return if (showStackTrace!!) Arrays.stream(ex.stackTrace).map { obj: StackTraceElement -> obj.toString() }
            .collect(Collectors.toList()) else null
    }

    @JvmOverloads
    fun generateResponseError(ex: Exception, message: ErrorCauses, additionalInfo: String? = null): ResponseError {
        val error = Error()
        var description = message.message
        if (additionalInfo != null) {
            description += additionalInfo
        }
        error.code = message.id
        error.description = description
        error.detail = ex.message
        error.stack = getStackTrace(ex)
        return ResponseError(error)
    }
}