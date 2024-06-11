package com.example.spiice.utils

import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter= DateTimeFormatter.ofPattern("MMM dd, yyyy")

fun convertDataFromLongToString (data: Long): String = DateFormat.getDateInstance(DateFormat.DEFAULT).format(data)

fun convertDataFromStringToLocalData (string: String): LocalDate = LocalDate.parse(string,formatter)

fun convertDataFromLocalDataToString (data: LocalDate): String = data.format(formatter)