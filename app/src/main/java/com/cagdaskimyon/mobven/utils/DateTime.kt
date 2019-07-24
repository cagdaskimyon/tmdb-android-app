package com.cagdaskimyon.mobven.utils

import org.joda.time.format.DateTimeFormat
import java.util.*


fun formatDate(date: String): String {
    val dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    val dt = dateTimeFormatter.parseDateTime(date)
    return dt.toString("MMM dd, yyyy", Locale.US)
}