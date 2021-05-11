package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil
import jp.small_java_world.testopegen.util.generateRandomLetterOrDigit

class TextOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_TEXT
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        val inputValue = generateRandomLetterOrDigit(4)

        testOperationList.add("//テキストボックスへの入力")
        SelenideUtil.inputTextByCssSelector(cssSelector!!, inputValue)

        val inputOperation = CommonDef.INPUT_VALUE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
            .replace(CommonDef.INPUT_VALUE, inputValue)
        testOperationList.add(inputOperation)
        testOperationList.add("")

        testOperationList.add("//テキストボックスへ入力した値の検証")
        SelenideUtil.shouldBeValueByCssSelector(cssSelector, inputValue)

        val confirmOperation = CommonDef.CONFIRM_VALUE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
            .replace(CommonDef.INPUT_VALUE, inputValue)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        return testOperationList;
    }
}