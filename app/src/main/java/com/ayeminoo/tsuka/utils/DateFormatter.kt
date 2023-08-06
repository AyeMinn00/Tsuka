package com.ayeminoo.tsuka.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class DateFormatter {

    fun formatUpdatedDateTime(milliSec: Long): String {
        val format = SimpleDateFormat("dd/MM/yyyy , hh:mm a", Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }
        val date = Date(milliSec)
        return format.format(date)
    }

    fun current() = formatUpdatedDateTime(System.currentTimeMillis())


}