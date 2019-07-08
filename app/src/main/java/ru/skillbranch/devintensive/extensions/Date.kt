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
    DAY
}