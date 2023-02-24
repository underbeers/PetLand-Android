package com.petland.app.features.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petland.app.data.model.remote.body.User
import com.petland.app.data.repository.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthorizationRepository): ViewModel() {
    fun signUp(user: User) {
        viewModelScope.launch { repository.signUp(user)}
    }
}