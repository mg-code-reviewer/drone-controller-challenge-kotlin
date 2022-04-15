package com.sumup.drone_challenge.logic

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseError(error: Error) {
    @JsonProperty("errors")
    private var errors: List<Error>? = null

    init {
        errors = listOf(error)
    }
}