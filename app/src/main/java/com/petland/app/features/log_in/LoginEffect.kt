package com.petland.app.features.log_in


sealed interface LoginEffect {
    object NavigateToAccount: LoginEffect
    object NavigateToSignUp: LoginEffect
}