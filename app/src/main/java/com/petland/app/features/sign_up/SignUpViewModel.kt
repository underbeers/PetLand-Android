package com.petland.app.features.sign_up

import androidx.lifecycle.viewModelScope
import com.petland.app.data.model.remote.body.UserBody
import com.petland.app.data.repository.AuthorizationRepository
import com.petland.app.ext.collect
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import com.petland.app.util.generateReceiveCode
import com.petland.app.util.validator.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor(private val authorizationRepository: AuthorizationRepository) :
    BaseViewModel<SignUpState, SignUpEffect>(SignUpState()) {
    init {
        collect(state, ::checkFilledFieldsInBothSteps)
    }

    fun onSignUp() {
        viewModelScope.launch {
            val signUpResponse = authorizationRepository.signUp(
                UserBody(
                    name = state.value.firstName.value,
                    surname = state.value.secondName.value,
                    email = state.value.email.value,
                    password = state.value.password.value,
                )
            )
            signUpResponse.onEach { response ->
                when (response) {
                    is DataState.Success -> {
                        setState {
                            copy(
                                isSignedUpSuccessfully = true,
                                isErrorAppeared = false
                            )
                        }
                        postEffect(SignUpEffect.NavigateToLogIn)
                    }
                    is DataState.Error -> {
                        setState {
                            copy(
                                isSignedUpSuccessfully = false,
                                isErrorAppeared = true
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun checkFilledFieldsInBothSteps(signUpState: SignUpState) = setState {
        copy(
            isCountDownStarted = seconds != 0,
            isPossibleToSendCode = email.value.isNotEmpty() && email.isAccepted && isCountDownStarted.not(),
            isEmailVerified = sendCode.value == receiveCode && receiveCode.isNotEmpty() && emailToVerify == email.value,
            isAllowedMoveToSecondStep = firstName.value.isNotEmpty() && secondName.value.isNotEmpty()
                    && firstName.isAccepted && secondName.isAccepted,
            isAllowedMoveToThirdStep = email.value.isNotEmpty() && sendCode.value.isNotEmpty() && sendCode.isAccepted && email.isAccepted && isEmailVerified,
            isAllowedToFinishSignUp = password.value.isNotEmpty() && repeatPassword.value.isNotEmpty() && isCheckBoxChecked && password.isAccepted && repeatPassword.isAccepted
        )
    }

    fun onNavigateToLogIn() {
        postEffect(SignUpEffect.NavigateToLogIn)
    }

    fun onNavigateToNextStep() = setState {
        copy(
            currentStep = state.value.currentStep.inc()
        )
    }

    fun onFirstNameChange(firstName: String) = setState {
        copy(
            firstName = Validator.validateName(firstName)
        )
    }

    fun onLastNameChange(lastName: String) = setState {
        copy(
            secondName = Validator.validateName(lastName)
        )
    }

    fun onEmailChange(email: String) = setState {
        copy(
            email = Validator.validateEmail(email)
        )
    }

    fun onSendCodeChange(code: String) = setState {
        copy(
            sendCode = Validator.validateCode(code, receiveCode),
        )
    }

    fun onEmailVerify() {
        viewModelScope.launch {
            val generatedVerifyCode = generateReceiveCode()
            val email = state.value.email.value
            setState { copy(emailToVerify = email) }
            val sendCodeResponse = authorizationRepository.sendCode(
                email = email,
                code = generatedVerifyCode
            )
            sendCodeResponse.onEach { response ->
                if (response is DataState.Success) {
                    setState { copy(receiveCode = generatedVerifyCode) }
                }
            }.launchIn(viewModelScope)
        }
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch(Dispatchers.IO) {
            for (second in SEND_CODE_COUNTDOWN downTo 0) {
                setState { copy(seconds = second) }
                delay(SECOND)
            }
        }
    }

    fun onPasswordChange(password: String) = setState {
        copy(
            password = Validator.validatePassword(password),
            repeatPassword = Validator.validateRepeatPassword(
                password, state.value.repeatPassword.value
            )
        )
    }

    fun onPasswordVisibilityChange() = setState {
        copy(isPasswordVisible = state.value.isPasswordVisible.not())
    }

    fun onRepeatedPasswordVisibilityChange() = setState {
        copy(isRepeatPasswordVisible = state.value.isRepeatPasswordVisible.not())
    }

    fun onRepeatPasswordChange(repeatPassword: String) = setState {
        copy(
            repeatPassword = Validator.validateRepeatPassword(
                password.value, repeatPassword
            )
        )
    }

    fun onCheckedChange(checked: Boolean) = setState {
        copy(
            isCheckBoxChecked = checked
        )
    }

    private companion object {
        const val SEND_CODE_COUNTDOWN = 30
        const val SECOND = 1000L
    }
}

