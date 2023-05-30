package com.petland.app.features.profile

import androidx.lifecycle.viewModelScope
import com.petland.app.data.repository.AccountRepository
import com.petland.app.data.store.Store
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import com.petland.app.util.ProfileType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val accountRepository: AccountRepository, private val store: Store) :
    BaseViewModel<ProfileState, ProfileEffect>(ProfileState()) {
    init {
        getUserInfo()
    }

    fun onAuthorize() {
        postEffect(ProfileEffect.NavigateToAuthorization)
    }

    fun onPetsClick() {
        postEffect(ProfileEffect.NavigateToPets)
    }

    fun onAdvertClick() {
        postEffect(ProfileEffect.NavigateToUserAdverts)
    }

    fun onRatingClick() {
        postEffect(ProfileEffect.NavigateToRating)
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val accessToken = store.accessToken.first()
            val userInfo = accountRepository.getUserInfo(accessToken)
            userInfo.onEach { response ->
                if (response is DataState.Success) {
                    setState {
                        copy(
                            avatar = response.data.imageLink.ifEmpty { state.value.defaultAvatar },
                            name = response.data.name,
                            surname = response.data.surname,
                            profileType = ProfileType.USER_PROFILE,
                            isLoading = false
                        )
                    }
                } else {
                    setState {
                        copy(
                            profileType = ProfileType.UNAUTHORIZED,
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}