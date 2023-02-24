package com.petland.app.features.sign_up

sealed interface SignUpEffect {
    object NavigateToLogIn: SignUpEffect
}