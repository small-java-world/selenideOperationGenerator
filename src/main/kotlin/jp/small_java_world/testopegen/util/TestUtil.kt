package jp.small_java_world.testopegen.util

import org.apache.commons.text.CharacterPredicate
import org.apache.commons.text.RandomStringGenerator

fun generateRandomLetterOrDigit(length: Int): String {
    return RandomStringGenerator.Builder().withinRange('0'.toInt(), 'z'.toInt())
        .filteredBy(CharacterPredicate { codePoint: Int ->
            Character.isLetterOrDigit(
                codePoint
            )
        })
        .build().generate(length)
}