package com.petland.app.data

object ApiURL {
    const val BACKEND_URL = "http://petland-backend.underbeers.space/api/v1/"
    private const val WEB_URL = "http://petland.underbeers.space/"
    const val SIGN_UP_USER = "registration/new"
    const val LOG_IN_USER = "login"
    const val SEND_CODE = "email/code"
    const val GET_USER_INFO = "user/info"
    const val RESTORE_PASSWORD = "${WEB_URL}password-recovery"
    const val GET_PETS = "petCards"
    const val GET_PET_CARDS = "petCards/main"
    const val CREATE_PET_CARD = "petCards/new"
    const val UPDATE_PET_CARD = "petCards/update"
    const val DELETE_PET = "petCards/delete"
    const val PET_TYPES = "petTypes"
    const val BREEDS = "breeds"
}