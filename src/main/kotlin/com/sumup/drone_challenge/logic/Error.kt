package com.sumup.drone_challenge.logic

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Objects

class Error {
    @Schema(name = "code", type = "integer", format = "int32", example = "1001")
    @JsonProperty("code")
    var code = 0

    @Schema(name = "description", type = "string", example = "Error description")
    @JsonProperty("description")
    var description: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "detail", type = "integer", example = "Error detail")
    @JsonProperty("detail")
    var detail: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var stack: List<String>? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val error = o as Error
        return code == error.code && description == error.description && detail == error.detail && stack == error.stack
    }

    override fun hashCode(): Int {
        return Objects.hash(code, description, detail, stack)
    }

    override fun toString(): String {
        return "Error{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", detail='" + detail + '\'' +
                ", stack=" + stack +
                '}'
    }
}