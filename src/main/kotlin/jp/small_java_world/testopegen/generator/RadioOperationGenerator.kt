package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_SELECTED_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE
import jp.small_java_world.testopegen.define.CommonDef.Companion.SELECT_RADIO_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.TARGET_CSS_SELECTOR
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.selectByCssSelector
import jp.small_java_world.testopegen.util.selectRadioByCssSelector
import jp.small_java_world.testopegen.util.shouldBeSelectedByCssSelector

class RadioOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_RADIO
    }

    override fun generateCustomOperation(cssSelector: String?, testOperationList : MutableList<String>): Collection<String> {
        var radioName = selectByCssSelector(cssSelector!!).getAttribute("name")
        var radioValue = selectByCssSelector(cssSelector).value

        testOperationList.add("//ラジオボタンの選択")
        selectRadioByCssSelector("input[name=$radioName]", radioValue!!)
        var selectOperation = SELECT_RADIO_TEMPLATE.replace(TARGET_CSS_SELECTOR, "input[name=$radioName]").replace(INPUT_VALUE, radioValue)
        testOperationList.add(selectOperation)
        testOperationList.add("")

        testOperationList.add("//テキストボックスへ入力した値の検証")
        shouldBeSelectedByCssSelector(cssSelector)
        val confirmOperation = CONFIRM_SELECTED_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        return testOperationList;
    }
}