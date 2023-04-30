package com.petland.app.features.profile

sealed interface ProfileEffect {
    object NavigateToAuthorization: ProfileEffect
}