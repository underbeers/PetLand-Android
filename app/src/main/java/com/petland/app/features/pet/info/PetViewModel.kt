package com.petland.app.features.pet.info

import androidx.lifecycle.viewModelScope
import com.petland.app.data.repository.ProfileRepository
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import com.petland.app.util.PetScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) :
    BaseViewModel<PetState, PetEffect>(PetState()) {

    init {
        getPetCards()
    }

    fun setUserInfo(name: String, avatarLink: String) {
        setState {
            copy(
                userName = name,
                avatar = avatarLink
            )
        }
    }

    fun onBulletinBoardClick() {
        postEffect(PetEffect.NavigateToBulletinBoard)
    }

    fun onPetAddClick() {
        postEffect(PetEffect.NavigateToAddPet)
    }

    private fun getPetCards() {
        viewModelScope.launch{
            val petCards = profileRepository.getPetCards()
            petCards.onEach { response ->
                if (response is DataState.Success) {
                    setState {
                        copy(
                            pets = response.data,
                            petScreenType = PetScreenType.PET_AVAILABLE
                        )
                    }
                } else {
                    setState {
                        copy(
                            petScreenType = PetScreenType.PET_ABSENCE
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onEditPetClick() {
        postEffect(PetEffect.NavigateToEditPet)
    }
}