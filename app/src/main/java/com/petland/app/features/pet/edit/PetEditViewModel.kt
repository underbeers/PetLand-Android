package com.petland.app.features.pet.edit

import androidx.lifecycle.viewModelScope
import com.petland.app.data.model.remote.response.Breed
import com.petland.app.data.model.remote.response.PetType
import com.petland.app.data.repository.PetCardRepository
import com.petland.app.data.repository.ProfileRepository
import com.petland.app.features.base.BaseViewModel
import com.petland.app.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetEditViewModel @Inject constructor(private val petEditRepository: PetCardRepository) :
    BaseViewModel<PetEditState, PetEditEffect>(PetEditState()) {

    init {
        getData()
    }

    fun onNameChange(name: String) {
        setState { copy(petName = name) }
    }

    fun onTypeSelect(type: PetType) {
        setState { copy(type = type) }
    }

    fun onBreedSelected(breed: Breed) {
        setState { copy(breed = breed) }
    }

    fun onGenderSelected(gender: String) {
        setState { copy(gender = gender) }
    }

    private fun getData() {
        viewModelScope.launch {
            petEditRepository.getPetType().onEach { response ->
                if (response is DataState.Success) {
                    setState {
                        copy(
                            typeList = response.data
                        )
                    }
                }
            }.launchIn(viewModelScope)

            petEditRepository.getBreed().onEach { response ->
                if (response is DataState.Success) {
                    setState {
                        copy(
                            breeds = response.data
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}