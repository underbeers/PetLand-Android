package com.petland.app.features.main

import com.petland.app.features.base.BaseViewModel

class MainViewModel() : BaseViewModel<MainState, MainEffect>(MainState()) {

    fun onSettingsClicked() {
        postEffect(MainEffect.NavigateToSettings)
    }

    fun onNavigateBack() {
        postEffect(MainEffect.NavigateBack)
    }
}