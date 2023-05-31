package com.petland.app.features.pet.edit

import com.petland.app.data.model.remote.response.Breed
import com.petland.app.data.model.remote.response.PetType

data class PetEditState(
    val petName: String = "",
    val typeList: List<PetType> = listOf(),
    val type: PetType = PetType(),
    val breeds: List<Breed> = listOf(),
    val breed: Breed = Breed(),
    val genders: List<String> = listOf("мальчик", "девочка"),
    val gender: String = "",
)
