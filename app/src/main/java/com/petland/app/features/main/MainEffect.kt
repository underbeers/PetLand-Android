package com.petland.app.features.main

sealed interface MainEffect {
    object NavigateToSettings: MainEffect
    object NavigateBack: MainEffect
}