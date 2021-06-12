package com.br.events.ui.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateFormatted(): String {
    val dateFormatted = SimpleDateFormat("dd MMMM yyyy HH:mm")
    val date = Date(this)
    return dateFormatted.format(date)
}