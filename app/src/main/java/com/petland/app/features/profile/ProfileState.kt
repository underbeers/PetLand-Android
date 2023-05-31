package com.petland.app.features.profile

import com.petland.app.data.model.remote.response.Pet
import com.petland.app.util.ProfileType

data class ProfileState(
    var profileType: ProfileType = ProfileType.UNAUTHORIZED,
    val name: String = "",
    val surname: String = "",
    val avatar: String = "",
    var isOtherProfile: Boolean = false,
    val isLoading: Boolean = true,
    val pets: List<Pet> = listOf(),
    val rating: Double = 5.0,
    val reviewsAmount: Int = 0,
    val advertAmount: Int = 0,
    val description: String = "Lörem ipsum kuns kudirin. Euren diar vägen miheten. Krorade prertad. Sahyren påpp berade fede. Trar sharenting i mikronar obös. Barriärvård paralödade. Kontratos. Suprasamma kar osam till agnostitos. Terabel dingen, kede parartad. Famis hysa i didoligen. ",
    val defaultAvatar: String = "https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg"
)
