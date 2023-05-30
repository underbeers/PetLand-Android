package com.petland.app.features.profile

sealed interface ProfileEffect {
    object NavigateToAuthorization: ProfileEffect
    object NavigateToRating: ProfileEffect
    object NavigateToPets: ProfileEffect
    object NavigateToUserAdverts: ProfileEffect
}