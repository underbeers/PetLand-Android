package com.petland.app.util.validator

import android.util.Patterns

object Validator {
    private val upperCaseRegex = "^(?=.*[A-Z]).+\$".toRegex()
    private val digitRegex = "^(?=.*\\d).+\$".toRegex()
    private val specialCharRegex = "^(?=.*[!@#\$%^&*(),.?\":{}|<>]).+\$".toRegex()
    private const val minPasswordLength = 8
    private const val sendCodeLength = 6

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

    fun validateRepeatPassword(password: String, repeat: String) = AcceptableValue(
        value = repeat,
        acceptance = when (password) {
            repeat -> Acceptance.ACCEPTED
            else -> Acceptance.MISMATCH
        }
    )

    fun validateName(name: String) = AcceptableValue(
        value = name,
        acceptance = when {
            name.isEmpty() -> Acceptance.EMPTY
            name.matches(specialCharRegex) -> Acceptance.UNSUPPORTED_CHAR
            name.matches(digitRegex) -> Acceptance.DIGITS_UNSUPPORTED
            else -> Acceptance.ACCEPTED
        }
    )

    fun validateCode(code: String, receiveCode: String = "") = AcceptableValue(
        value = code,
        acceptance = when {
            code.isEmpty() -> Acceptance.EMPTY
            code.length != sendCodeLength -> Acceptance.AT_LEAST_SIX_DIGITS_IN_SENT_CODE
            receiveCode.isNotEmpty() && receiveCode != code -> Acceptance.MISMATCH
            else -> Acceptance.ACCEPTED
        }
    )

}