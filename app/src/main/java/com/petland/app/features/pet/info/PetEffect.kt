package com.petland.app.features.pet.info

sealed interface PetEffect {
    object NavigateToBulletinBoard : PetEffect

    object NavigateToAddPet : PetEffect

    object NavigateToEditPet : PetEffect
}