package com.petland.app.features.user_advert

import com.petland.app.data.model.local.Advert

data class UserAdvertState(
    val advert: List<Advert> = listOf()
)
