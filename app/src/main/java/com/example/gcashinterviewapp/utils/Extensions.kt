package com.example.gcashinterviewapp.utils

import android.util.Log
import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


private val PUNCTUATION = listOf(", ", "; ", ": ", " ")


fun String.convertKelvinToCelsius() : String{
    var kelvinValue : Double = this.toDouble()
    var celciusValue = kelvinValue - 273.15
    return DecimalFormat("0.00").format(celciusValue).toString() + " Celsius"
}

fun String.convertTimeToDate() : String{
    //dd/M/yyyy and hh:mm:ss
    val simpleDate = SimpleDateFormat("hh:mm a")
    var currentDate = "0"
    try{
        val ts = Timestamp(this.toLong() * 1000L)
        currentDate = simpleDate.format(ts.time)
    }catch (e : Exception){
        Log.d("time ", e.printStackTrace().toString())
    }
    return currentDate
}

fun String.smartTruncate(length: Int): String {
    val words = split(" ")
    var added = 0
    var hasMore = false
    val builder = StringBuilder()
    for (word in words) {
        if (builder.length > length) {
            hasMore = true
            break
        }
        builder.append(word)
        builder.append(" ")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)) {
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if (hasMore) {
        builder.append("...")
    }
    return builder.toString()
}


/**
 * Map DatabaseVideos to domain entities
 */
/*fun List<VideoObj>.asDomainModel(): List<Video> {
    return map {
        Video(
            id = 0,
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}*/


/*val city = response.body()?.name.toString()
val country = response.body()?.sys?.country.toString()
val currentTemp = response.body()?.main?.temp
val sunriseTime = response.body()?.sys?.sunrise
val sunsetTime = response.body()?.sys?.sunset
val iconId = response.body()?.weather.toString()

fun WeatherItem.asDomainModel() : WeatherItem{
    return map{
        WeatherItem(id= 0,
        city = it.)
    }
}*/
