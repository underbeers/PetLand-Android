package com.petland.app.features.main

import androidx.lifecycle.viewModelScope
import com.petland.app.data.store.Store
import com.petland.app.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val store: Store
) : BaseViewModel<MainState, MainEffect>(MainState()) {

    init {
        viewModelScope.launch {
            store.accessToken.collect { token ->
                setState { copy(isAuthorized = token.isNotEmpty()) }
            }
        }
    }

    fun onLogoutClicked() {
        viewModelScope.launch {
            store.clearSavedData()
            postEffect(MainEffect.NavigateToProfile)
        }
    }

    fun onPetAddClick() {
        postEffect(MainEffect.NavigateToPetAdd)
    }

    fun onNavigateBack() {
        postEffect(MainEffect.NavigateBack)
    }
}