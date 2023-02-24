package com.petland.app.features.sign_up

import androidx.lifecycle.viewModelScope
import com.petland.app.data.model.remote.body.User
import com.petland.app.data.repository.AuthorizationRepository
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import com.petland.app.util.validator.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import vn.takomo.app.ext.collect
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor(private val repository: AuthorizationRepository) :
    BaseViewModel<SignUpState, SignUpEffect>(SignUpState()) {
    init {
        collect(state, ::checkFilledFieldsInBothSteps)
    }

    fun onSignUp() {
        viewModelScope.launch {
            val signUpResponse = repository.signUp(
                User(
                    name = state.value.firstName.value,
                    surname = state.value.secondName.value,
                    email = state.value.email.value,
                    password = state.value.password.value,
                )
            )
            signUpResponse.onEach { response ->
                when (response) {
                    is DataState.Success -> {
                        postEffect(SignUpEffect.NavigateToLogIn)
                        setState { copy(
                            isSignedUpSuccessfully = true,
                            isErrorAppeared = false
                        ) }
                    }
                    is DataState.Error -> {
                        setState { copy(
                            isSignedUpSuccessfully = false,
                            isErrorAppeared = true
                        ) }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun checkFilledFieldsInBothSteps(signUpState: SignUpState) = setState {
        copy(
            isPossibleToSendCode = email.value.isNotEmpty() && email.isAccepted && isCountDownStarted.not(),
            isEmailVerified = sendCode.value == TEMP_CODE,
            isAllowedMoveToNextStep = isPossibleToSendCode && firstName.value.isNotEmpty() && secondName.value.isNotEmpty()
                    && firstName.isAccepted && secondName.isAccepted,
            isCountDownStarted = seconds != 0,
            isAllFieldsFilled = isAllowedMoveToNextStep && signUpState.password.isAccepted && signUpState.repeatPassword.isAccepted
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

    fun onNavigateToPreviousStep() = setState {
        copy(
            currentStep = state.value.currentStep.dec()
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
            sendCode = Validator.validateCode(code),
        )
    }

    fun onEmailVerify() {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            for(second in SEND_CODE_COUNTDOWN downTo  0){
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
                state.value.password.value, repeatPassword
            )
        )
    }

    fun onCheckedChange(checked: Boolean) = setState {
        copy(
            isCheckBoxChecked = checked
        )
    }

    fun onDialogExit() = setState {
        copy(
            isDialogDisplayed = false
        )
    }

    private companion object {
        const val TEMP_CODE = "654321"
        const val SEND_CODE_COUNTDOWN = 30
        const val SECOND = 1000L
    }
}

