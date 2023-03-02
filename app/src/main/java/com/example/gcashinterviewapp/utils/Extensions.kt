package com.example.gcashinterviewapp.utils

import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat

fun String.convertKelvinToCelsius() : String{
    var kelvinValue : Double = this.toDouble()
    var celciusValue = kelvinValue - 273.15
    return DecimalFormat("0.00").format(celciusValue).toString() + " Celsius"
}

fun String.convertTimeToDate(): String {
    //dd/M/yyyy and hh:mm:ss
    val simpleDate = SimpleDateFormat("hh:mm a")
    val ts = Timestamp(this.toLong() * 1000L)
    return simpleDate.format(ts.time)
}
