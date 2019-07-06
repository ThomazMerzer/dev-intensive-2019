package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        //TODO fix me
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //написать функцию, которая переводи кириллицу в латинские символы
    }

    fun toInitials(firstName: String? = null, lastName: String? = null): String? {

        val pairForNames = firstName to lastName

        var initials: String? = "${pairForNames.first?.getOrNull(0)?.toUpperCase()}, " +
                "${pairForNames.second?.getOrNull(0)?.toUpperCase()}"

        if(pairForNames.first.isNullOrBlank() && pairForNames.second.isNullOrBlank()) {
            initials = null
        } else if(pairForNames.first.isNullOrBlank() || pairForNames.second.isNullOrBlank()) {
            if(pairForNames.first.isNullOrBlank()) {
                initials = "${pairForNames.second?.getOrNull(0)?.toUpperCase()}"
            }
            else if(pairForNames.second.isNullOrBlank()) {
                initials = "${pairForNames.first?.getOrNull(0)?.toUpperCase()}"
            }
        }

        return initials
    }
}