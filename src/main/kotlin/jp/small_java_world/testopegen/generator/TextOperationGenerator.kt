package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil

class TextOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_TEXT
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        val inputValue = generateRandomLetterOrDigit(4)
        SelenideUtil.inputTextByCssSelector(cssSelector!!, inputValue)

        testOperationList.add("//テキストボックスへの入力")
        val inputOperation = CommonDef.INPUT_VALUE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
            .replace(CommonDef.INPUT_VALUE, inputValue)
        testOperationList.add(inputOperation)
        testOperationList.add("")

        SelenideUtil.shouldBeValueByCssSelector(cssSelector, inputValue)
        testOperationList.add("//テキストボックスへ入力した値の検証")
        val confirmOperation = CommonDef.CONFIRM_VALUE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
            .replace(CommonDef.INPUT_VALUE, inputValue)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        return testOperationList;
    }
}