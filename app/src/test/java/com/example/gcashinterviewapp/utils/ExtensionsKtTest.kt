package com.example.gcashinterviewapp.utils

import org.junit.Assert.*
import org.junit.Test
import kotlin.NumberFormatException

internal class ExtensionsKtTest {

    @Test
    fun convertKelvinToCelsius_validOutput() {
        val output = "306.15".convertKelvinToCelsius()
        assertEquals("33.00 Celsius",output)
    }

    @Test
    fun convertKelvinToCelsius_invalidInput() {
        val output = "306.15".convertKelvinToCelsius()
        assertNotEquals("33.00",output)
    }

    @Test(expected = NumberFormatException::class)
    fun convertKelvinToCelsius_numberFormatException() {
        "".convertKelvinToCelsius()
    }


    @Test
    fun convertTimeToDate_validOutput() {
        val output = "1677720452".convertTimeToDate()
        assertEquals("06:57 AM",output)
    }

    @Test
    fun convertTimeToDate_invalidOutput() {
        val output = "1677720452".convertTimeToDate()
        assertNotEquals("06:00 AM",output)
    }

    @Test(expected = RuntimeException::class)
    fun convertTimeToDate_invalidInput() {
        "".convertTimeToDate()
    }


}