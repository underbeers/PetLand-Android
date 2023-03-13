package com.petland.app.features.log_in

import androidx.lifecycle.viewModelScope
import com.petland.app.data.repository.AuthorizationRepository
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import com.petland.app.util.validator.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.takomo.app.ext.collect
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val repository: AuthorizationRepository) :
    BaseViewModel<LoginState, LoginEffect>(LoginState()) {
    init {
        collect(state, ::checkFilledFields)
    }

    fun onLogIn() {
        setState { copy(
            isLoading = true,
            isDialogDisplayed = true
        ) }
        viewModelScope.launch {
            val login = state.value.login
            val password = state.value.password
            val logInUserResponse = repository.logIn(login.value, password.value)
            logInUserResponse.onEach { response ->
                when (response) {
                    is DataState.Success -> {
                        setState { copy(
                            isLoggedSuccessfully = true,
                            isErrorAppeared = false,
                        ) }
                        postEffect(LoginEffect.NavigateToAccount)
                    }
                    is DataState.Error -> {
                        Timber.e(response.exception, "login error")
                        setState { copy(
                            isErrorAppeared = true,
                            isLoggedSuccessfully = false,
                        ) }
                    }
                }
            }.launchIn(viewModelScope)
        }
        setState { copy(isLoading = false) }
    }

    fun onLoginChange(login: String) = setState {
        copy(
            login = Validator.validateEmail(login)
        )
    }

    fun onPasswordChange(password: String) = setState {
        copy(
            password = Validator.validatePassword(password)
        )
    }

    fun onDialogExit() = setState {
        copy(
            isDialogDisplayed = false
        )
    }

    fun onPasswordVisibilityChange() = setState {
        copy(
            isPasswordVisible = state.value.isPasswordVisible.not()
        )
    }

    private fun checkFilledFields(state: LoginState) = setState {
        copy(
            isLogInButtonEnabled = state.login.isAccepted && state.password.isAccepted && state.login.value.isNotEmpty() && state.password.value.isNotEmpty()
        )
    }

    fun onCheckedChange(checked: Boolean) = setState {
        copy(
            isCheckBoxChecked = checked
        )
    }

    fun onSignUp() {
        postEffect(LoginEffect.NavigateToSignUp)
    }
}