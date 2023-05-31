package com.petland.app.features.pet.edit

sealed interface PetEditEffect {
    object NavigateToPetsScreen: PetEditEffect
}