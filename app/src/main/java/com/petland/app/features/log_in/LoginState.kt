package com.petland.app.features.log_in

import com.petland.app.util.validator.AcceptableValue

data class LoginState(
    val login: AcceptableValue = AcceptableValue(),
    val password: AcceptableValue = AcceptableValue(),
    val isPasswordVisible: Boolean = false,
    val isCheckBoxChecked: Boolean = false,
    val isLoading: Boolean = false,
    val isLoggedSuccessfully: Boolean = false,
    val isErrorAppeared:Boolean = false,
    val isDialogDisplayed: Boolean = false,
    val isLogInButtonEnabled: Boolean = false,
)