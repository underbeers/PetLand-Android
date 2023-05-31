package com.petland.app.features.main

sealed interface MainEffect {
    object NavigateToProfile: MainEffect
    object NavigateBack: MainEffect

    object NavigateToPetAdd: MainEffect
}