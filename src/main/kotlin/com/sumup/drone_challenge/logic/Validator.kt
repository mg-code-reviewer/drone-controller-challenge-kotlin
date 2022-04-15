package com.sumup.drone_challenge.logic

import javax.validation.ConstraintViolationException
import javax.validation.Validation
import javax.validation.Validator
import org.springframework.stereotype.Service

@Service
class Validator {
    private val validator: Validator

    init {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    fun validate(`object`: Any) {
        val violations = validator.validate(`object`)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}