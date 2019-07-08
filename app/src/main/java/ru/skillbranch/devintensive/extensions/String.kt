package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    var index = 0

    if(value > this.length) throw StringIndexOutOfBoundsException("Число больше длины строки value: $value")

    return if(this.last() == ' ' && value != 0) {
        for (char in this) {
            if (char != ' ') {
                index++
            }
        }
        println("${this.substring(0, index)}...")
        "${this.substring(0, index)}..."
    }
    else if(value != 0){
        println("${this.substring(0, value)}...")
        "${this.substring(0, value)}..."
    }
    else if(value == 0){
        index = this.length
        println("${this.substring(0, index)}...")
        "${this.substring(0, index)}..."
    }
    else {
        println(this)
        this
    }
}

fun String.stripHtml(): String {
    return this
}