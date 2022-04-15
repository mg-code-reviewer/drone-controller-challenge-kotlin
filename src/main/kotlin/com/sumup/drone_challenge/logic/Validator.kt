package com.sumup.drone_challenge.logic

import javax.validation.ConstraintViolationException
import javax.validation.Validation
import org.springframework.stereotype.Service

@Service
class Validator {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    fun validate(`object`: Any) {
        val violations = validator.validate(`object`)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
