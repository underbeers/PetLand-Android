package com.petland.app.util

fun generateReceiveCode(): String {
    val codeList = List(6) { (0..9).random() }
    return codeList.joinToString(separator = "", prefix = "", postfix = "")
}