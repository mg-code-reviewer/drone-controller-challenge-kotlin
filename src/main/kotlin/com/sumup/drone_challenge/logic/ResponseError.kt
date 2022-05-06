package com.sumup.drone_challenge.logic

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseError(error: Error) {
    @JsonProperty("errors")
    private val errors: List<Error>

    init {
        errors = listOf(error)
    }
}
