package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil

class RadioOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_RADIO
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        var radioName = SelenideUtil.getNameByCssSelector(cssSelector!!)
        var radioValue = SelenideUtil.getValueByCssSelector(cssSelector)

        testOperationList.add("//ラジオボタンの選択")
        SelenideUtil.selectRadioByCssSelector("input[name=$radioName]", radioValue!!)

        var selectOperation =
            CommonDef.SELECT_RADIO_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, "input[name=$radioName]")
                .replace(CommonDef.INPUT_VALUE, radioValue)
        testOperationList.add(selectOperation)
        testOperationList.add("")

        testOperationList.add("//ラジオボタンの選択の検証")
        SelenideUtil.shouldBeSelectedByCssSelector(cssSelector)

        val confirmOperation = CommonDef.CONFIRM_SELECTED_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        return testOperationList;
    }
}