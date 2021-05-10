package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.TARGET_CSS_SELECTOR
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.inputTextByCssSelector
import jp.small_java_world.testopegen.util.shouldBeValueByCssSelector

class TextOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_TEXT
    }

    override fun generateCustomOperation(cssSelector: String?, testOperationList : MutableList<String>): Collection<String> {
        val inputValue =generateRandomLetterOrDigit(4)
        inputTextByCssSelector(cssSelector!!, inputValue)

        testOperationList.add("//テキストボックスへの入力")
        val inputOperation = INPUT_VALUE_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector).replace(INPUT_VALUE, inputValue)
        testOperationList.add(inputOperation)
        testOperationList.add("")

        shouldBeValueByCssSelector(cssSelector, inputValue)
        testOperationList.add("//テキストボックスへ入力した値の検証")
        val confirmOperation = CONFIRM_VALUE_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector).replace(INPUT_VALUE, inputValue)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        return testOperationList;
    }
}