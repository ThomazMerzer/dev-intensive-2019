package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {

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