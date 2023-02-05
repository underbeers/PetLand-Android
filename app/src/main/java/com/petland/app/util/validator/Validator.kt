package com.petland.app.util.validator

import android.util.Patterns

object Validator {
    private val upperCaseRegex = "^(?=.*[A-Z]).+\$".toRegex()
    private val digitRegex = "^(?=.*\\d).+\$".toRegex()
    private val specialCharRegex =
        "^(?=.*[!@#\$%^&*(),.?\":{}|<>]).+\$".toRegex()
    private const val minPasswordLength = 8

    fun validateEmail(email: String): AcceptableValue = AcceptableValue(
        value = email,
        acceptance = when {
            email.matches(Patterns.EMAIL_ADDRESS.toRegex()) -> Acceptance.ACCEPTED
            else -> Acceptance.INCORRECT_EMAIL
        }
    )

    fun validatePassword(password: String): AcceptableValue = AcceptableValue(
        value = password,
        acceptance = when {
            password.length < minPasswordLength -> Acceptance.LENGTH_SHORT
            password.matches(upperCaseRegex).not() -> Acceptance.AT_LEAST_ONE_CAPITAL_LETTER
            password.matches(digitRegex).not() -> Acceptance.AT_LEAST_ONE_DIGIT
            password.matches(specialCharRegex).not() -> Acceptance.AT_LEAST_ONE_SPECIAL_SYMBOL
            else -> Acceptance.ACCEPTED
        }
    )


}