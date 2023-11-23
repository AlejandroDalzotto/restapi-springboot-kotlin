package com.dlz.demo.utils

import java.time.LocalDate

fun calculateAge(birthdate: LocalDate): Byte {
    try {
        val currentTime = LocalDate.now()

        val ageInYears = currentTime.year - birthdate.year

        if (birthdate.plusYears(ageInYears.toLong()).isAfter(currentTime)) {
            return (ageInYears - 1).toByte()
        }

        return ageInYears.toByte()
    } catch (e: Exception) {
        e.printStackTrace()
        return 0
    }
}