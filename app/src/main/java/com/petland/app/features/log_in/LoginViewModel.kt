package com.petland.app.features.log_in

import androidx.lifecycle.viewModelScope
import com.petland.app.data.repository.AuthorizationRepository
import com.petland.app.ext.collect
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import com.petland.app.util.validator.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val repository: AuthorizationRepository) :
    BaseViewModel<LoginState, LoginEffect>(LoginState()) {
    init {
        collect(state, ::checkFilledFields)
    }

    fun onLogIn() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            val login = state.value.login.value
            val password = state.value.password.value
            val logInResponse = repository.logIn(login, password)
            logInResponse.onEach { response ->
                when (response) {
                    is DataState.Success -> {
                        setState { copy(isErrorAppeared = false) }
                        postEffect(LoginEffect.NavigateToAccount)
                    }
                    is DataState.Error -> {
                        setState { copy(isErrorAppeared = true) }
                    }
                }
            }
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