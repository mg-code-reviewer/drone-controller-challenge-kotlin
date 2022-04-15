package com.sumup.drone_challenge.logic

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class Error(
    @Schema(name = "code", type = "integer", format = "int32", example = "1001")
    @JsonProperty("code")
    var code: Int = 0,

    @Schema(name = "description", type = "string", example = "Error description")
    @JsonProperty("description")
    var description: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "detail", type = "integer", example = "Error detail")
    @JsonProperty("detail")
    var detail: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var stack: List<String>? = null
)
