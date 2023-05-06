package com.petland.app.data

object ApiURL {
    const val BACKEND_URL = "http://petland-backend-k8s.underbeers.space/api/v1/"
    private const val WEB_URL = "http://petland-k8s.underbeers.space/"
    const val SIGN_UP_USER = "registration/new"
    const val LOG_IN_USER = "login"
    const val SEND_CODE = "email/code"
    const val GET_USER_INFO = "user/info"
    const val RESTORE_PASSWORD = "${WEB_URL}password-recovery"
}