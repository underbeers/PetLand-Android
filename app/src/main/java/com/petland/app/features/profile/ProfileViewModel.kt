package com.petland.app.features.profile

import com.petland.app.data.repository.AccountRepository
import com.petland.app.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val accountRepository: AccountRepository) :
    BaseViewModel<ProfileState, ProfileEffect>(ProfileState()) {
        fun onAuthorize() {
            postEffect(ProfileEffect.NavigateToAuthorization)
        }
}