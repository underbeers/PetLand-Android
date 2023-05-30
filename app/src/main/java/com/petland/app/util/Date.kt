package com.petland.app.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

val calendar: Calendar = Calendar.getInstance()

fun getMonthName(): String {
    val dateFormat = SimpleDateFormat("LLLL", Locale("ru"))
    val currentMonth: String = dateFormat.format(calendar.time)
    return monthsDeclensions[currentMonth]?.get(0) ?: ""
}

fun getCurrentYear(): Int {
    return calendar.get(Calendar.YEAR)
}

private val monthsDeclensions = mapOf(
    "январь" to listOf("января", "январю", "январем", "январе"),
    "февраль" to listOf("февраля", "февралю", "февралем", "феврале"),
    "март" to listOf("марта", "марту", "мартом", "марте"),
    "апрель" to listOf("апреля", "апрелю", "апрелем", "апреле"),
    "май" to listOf("мая", "маю", "маем", "мае"),
    "июнь" to listOf("июня", "июню", "июнем", "июне"),
    "июль" to listOf("июля", "июлю", "июлем", "июле"),
    "август" to listOf("августа", "августу", "августом", "августе"),
    "сентябрь" to listOf("сентября", "сентябрю", "сентябрем", "сентябре"),
    "октябрь" to listOf("октября", "октябрю", "октябрем", "октябре"),
    "ноябрь" to listOf("ноября", "ноябрю", "ноябрем", "ноябре"),
    "декабрь" to listOf("декабря", "декабрю", "декабрем", "декабре")
)