package com.petland.app.features.sign_up

import com.petland.app.util.validator.AcceptableValue

data class SignUpState(
    var currentStep: Int = 0,
    var receiveCode: String = "",
    val seconds: Int = 0,
    val isCountDownStarted: Boolean = false,
    val firstName: AcceptableValue = AcceptableValue(),
    val secondName: AcceptableValue = AcceptableValue(),
    val email: AcceptableValue = AcceptableValue(),
    val emailToVerify: String = "",
    val sendCode: AcceptableValue = AcceptableValue(),
    val password: AcceptableValue = AcceptableValue(),
    val repeatPassword: AcceptableValue = AcceptableValue(),
    val isPossibleToSendCode: Boolean = false,
    val isAllowedMoveToSecondStep: Boolean = false,
    val isAllowedMoveToThirdStep: Boolean = false,
    val isAllowedToFinishSignUp: Boolean = false,
    val isEmailVerified: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isRepeatPasswordVisible: Boolean = false,
    val isCheckBoxChecked: Boolean = false,
    val isAllFieldsFilled: Boolean = false,
    val isSignedUpSuccessfully: Boolean = false,
    val isErrorAppeared:Boolean = false,
)
