package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
//    var index = 0
//
//    if(value > this.length) throw StringIndexOutOfBoundsException("Число больше длины строки value: $value")
//
//    return if(this.last() == ' ' && value != 0) {
//        for (char in this) {
//            if (char != ' ') {
//                index++
//            }
//        }
//        println("${this.substring(0, index)}...")
//        "${this.substring(0, index)}..."
//    }
//    else if(value != 0){
//        println("${this.substring(0, value)}...")
//        "${this.substring(0, value)}..."
//    }
//    else if(value == 0){
//        index = this.length
//        println("${this.substring(0, index)}...")
//        "${this.substring(0, index)}..."
//    }
//    else {
//        println(this)
//        this
//    }

    val strSize = this.trim().length
    var str = this.trim().take(value)

    return when {
        strSize != str.length -> {
            if(str.toCharArray().last().isWhitespace()) {
                str = str.dropLast(1)
            }
            println("$str...")
            "$str..."
        }
        else ->  {
            println(str)
            str
        }
    }
}

fun String.stripHtml(): String {
    return this
}