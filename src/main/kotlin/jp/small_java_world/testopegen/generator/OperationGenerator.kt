package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_EXISTENCE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.TARGET_CSS_SELECTOR
import jp.small_java_world.testopegen.define.TargetElementType
import org.apache.commons.text.CharacterPredicate
import org.apache.commons.text.RandomStringGenerator

interface OperationGenerator {
    fun generateCustomOperation(cssSelector: String?, testOperationList : MutableList<String>): Collection<String>
    fun getElementType(): TargetElementType

    fun generateOperation(cssSelector: String?): MutableList<String> {
        var testOperationList = mutableListOf<String>()
        testOperationList.addAll(addConfirmOperation(cssSelector, getElementType().tagNameJp))
        generateCustomOperation(cssSelector, testOperationList)
        return testOperationList;
    }

    fun addConfirmOperation(cssSelector: String?, elementName: String): MutableList<String> {
        var result = mutableListOf<String>()
        result.add(" /**************** cssSelector ${cssSelector} の処理 start ****************/")

        val confirmOperation = CONFIRM_EXISTENCE_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector!!)
        result.add("//${elementName}の存在確認")
        result.add(confirmOperation)
        result.add("")
        return result
    }

    fun generateRandomLetterOrDigit(length: Int): String {
        return RandomStringGenerator.Builder().withinRange('0'.toInt(), 'z'.toInt())
            .filteredBy(CharacterPredicate { codePoint: Int ->
                Character.isLetterOrDigit(
                    codePoint
                )
            })
            .build().generate(length)
    }
}