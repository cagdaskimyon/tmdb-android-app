package com.cagdaskimyon.mobven.utils

import org.joda.time.format.DateTimeFormat
import java.util.*


fun formatDate(date: String?): String {
    return if (date.isNullOrBlank())
        "N/A"
    else {
        val dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        val dt = dateTimeFormatter.parseDateTime(date)
        dt.toString("MMM dd, yyyy", Locale.US)
    }
}