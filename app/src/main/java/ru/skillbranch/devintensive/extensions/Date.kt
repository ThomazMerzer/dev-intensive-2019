package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.YYYY"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val customDate = (this.time / 1000).toInt()
    val currentDate = (Date().time / 1000).toInt()
    //Перевёрнем число в положительную сторону для облегчения чтения
    var diff = (customDate - currentDate) - ((customDate - currentDate) * 2)

    val backWord = "назад"
    var tempString = ""
    var timeUnit = ""

    when {
        diff in -1..1 ->
            //Был только что
            tempString = "только что"
        diff in 1..44 -> {
            //Был несколько секунд назад
            timeUnit = "секунд"
            tempString = "несколько $timeUnit $backWord"
        }
        diff in 44..75 -> {
            //Был минуту назад
            timeUnit = "минуту"
            tempString = "$timeUnit $backWord"
        }
        diff in 75..2700 -> {
            //Был несколько минут назад
            timeUnit = "минут"
            diff /= 60
            tempString = "$diff $timeUnit $backWord"
        }
        diff in 2700..4500 -> {
            //Был час назад
            timeUnit = "час"
            tempString = "$timeUnit $backWord"
        }
        diff in 4500..79200 -> {
            //Был несколько часов назад
            timeUnit = "часов"
            diff = diff / 60 / 60
            tempString = "$diff $timeUnit $backWord"
        }
        diff in 79200..93600 -> {
            //Был день назад
            timeUnit = "день"
            tempString = "$timeUnit $backWord"
        }
        diff in 93600..31104000 -> {
            //Был несколько дней назад
            timeUnit = "дней"
            diff = diff / 60 / 60 / 24
            tempString = "$diff $timeUnit $backWord"
        }
        diff > 31104000 -> {
            //Был более 360 дней
            timeUnit = "года"
            tempString = "более $timeUnit $backWord"
        }
        else -> {
            tempString = "неизвестно когда"
        }
    }

    println("Пользователь был в сети $tempString")
    return tempString
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;
}

fun TimeUnits.plurals(value: Int): String {
    var tempStr = ""

    var one = arrayOf(1, 21, 31, 41, 51)
    val twoToFour = arrayOf(2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54)
    val five = arrayOf(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27,
        28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50, 55, 56, 57, 58, 59, 60)

    val timeUnit = this.name

    tempStr = when (timeUnit) {
        "SECOND" -> if (value in one) "секунду" else if (value in twoToFour) "секунды" else "секунд"
        "MINUTE" -> if (value in one) "минуту" else if (value in twoToFour) "минуты" else "минут"
        "HOUR" -> if (value in one) "ас" else if (value in twoToFour) "часа" else "часов"
        "DAY" -> if (value in one) "день" else if (value in twoToFour) "дня" else "дней"
        else -> ""
    }

    return "$value $tempStr"
}