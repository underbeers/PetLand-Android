package com.petland.app.features.profile

import com.petland.app.util.ProfileType

data class ProfileState(
    var profileType: ProfileType = ProfileType.UNAUTHORIZED
)
