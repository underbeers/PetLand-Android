package com.petland.app.util.validator

data class AcceptableValue(
    val value: String = "",
    val acceptance: Acceptance = Acceptance.ACCEPTED,
) {

    val isAccepted: Boolean
        get() = acceptance == Acceptance.ACCEPTED

    val isNotAccepted: Boolean get() = acceptance != Acceptance.ACCEPTED
}
