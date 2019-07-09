package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String? = null): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return if(firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            Pair(null, null)
        } else Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {

//        val kir = arrayOf(' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м',
//                'н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш','щ','ъ','ы','ь','э', 'ю','я','А',
//                'Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У',
//                'Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я','a','b','c','d','e','f','g','h',
//                'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C',
//                'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X',
//                'Y','Z')
//
//        val lat = arrayOf(" ","a","b","v","g","d","e","e","zh","z","i","i","k","l",
//                "m","n","o","p","r","s","t","u","f","h","c","ch","sh","sh","","i","","e","yu",
//                "ya","A","B","V","G","D","E","E","ZH","Z","I","I","K","L","M","N","O","P","R","S",
//                "T","U","F","H","C","CH","SH","SH","","I","","E","YU","YA","a","b","c","d","e",
//                "f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
//                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U",
//                "V","W","X","Y","Z")
//
//        var newPayload = StringBuilder()
//        for(p in 0 until payload.length) {
//            for(o in 0 until kir.size) {
//                if(payload[p] == kir[o]) {
//                    newPayload.append(lat[o])
//                }
//            }
//        }
//
//
//        return newPayload.toString()

        val map = hashMapOf<Char, String>('а' to "a", 'б' to "b", 'в' to "v", 'г' to "g",
            'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l",
            'м' to "m", 'н' to "n", 'о' to "o", 'г' to "g", 'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f",
            'х' to "h", 'ц' to "c", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh'", 'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e",
            'ю' to "yu", 'я' to "ya")

        val newPayload = StringBuilder()
        payload.toCharArray().forEach { char ->
            if(char.isWhitespace()) {
                newPayload.append(divider)
                return@forEach
            }

            if(!map.keys.contains(char.toLowerCase())) {
                newPayload.append(char)
                return@forEach
            }

            val toAdd = map[char.toLowerCase()]?.run {
                if(char.isUpperCase()) {
                    if(length <=1 ) {
                        toUpperCase()
                    } else {
                        first().toUpperCase().plus(drop(1))
                    }
                } else this
            }
            newPayload.append(toAdd)
        }
        return newPayload.toString()
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