package com.petland.app.features.pet.info

import com.petland.app.data.model.remote.response.Pet
import com.petland.app.util.PetScreenType

data class PetState(
    val isLoading: Boolean = true,
    val petScreenType: PetScreenType = PetScreenType.PET_ABSENCE,
    val avatar: String = "",
    val userName: String = "",
    val surName: String = "",
    val pets: List<Pet> = listOf()
)