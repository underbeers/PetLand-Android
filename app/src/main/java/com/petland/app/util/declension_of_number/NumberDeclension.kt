package com.petland.app.util

import com.petland.app.util.declension_of_number.NumberForm

fun getProperFormOfNumber(number: Int): NumberForm {
    val remainder = number % 10
    val isInRangeFromElevenToFourteen: Boolean = number / 10 % 10 == 1
    val isDigitFromOneToFour = 2..4
    if (isInRangeFromElevenToFourteen.not()) {
        if (remainder in isDigitFromOneToFour) return NumberForm.FROM_TWO_TO_FOUR
        if (remainder == 1) return NumberForm.ONE
    }
    return NumberForm.ANY
}