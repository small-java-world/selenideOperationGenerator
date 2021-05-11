package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil

class SelectOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.SELECT
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        val options = SelenideUtil.selectListByCssSelector("$cssSelector > option")

        for (option in options) {
            SelenideUtil.selectOptionByValueByCssSelector(cssSelector!!, option.value!!)
            SelenideUtil.selectOptionByCssSelector(cssSelector!!, option.text())
            SelenideUtil.shouldBeValueByCssSelector(cssSelector!!, option.value!!)

            testOperationList.add("//SELECTボックスを値で選択 value=$option.value")
            val selectOptionByValueOperation =
                CommonDef.SELECT_OPTION_BY_VALUE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector!!)
                    .replace(CommonDef.INPUT_VALUE, option.value!!)
            testOperationList.add(selectOptionByValueOperation)
            testOperationList.add("")

            testOperationList.add("//SELECTボックスをオプションで選択 option=${option.text()}")
            val selectOptionOperation =
                CommonDef.SELECT_SELECT_OPTION_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector!!)
                    .replace(CommonDef.INPUT_VALUE, option.text())
            testOperationList.add(selectOptionOperation)
            testOperationList.add("")

            testOperationList.add("//SELECTボックスの選択確認")
            val confirmSelectOperation = CommonDef.CONFIRM_SELECTED_TEMPLATE.replace(
                CommonDef.TARGET_CSS_SELECTOR,
                "$cssSelector > option[value='${option.value!!}']"
            ).replace(CommonDef.INPUT_VALUE, option.text())
            testOperationList.add(confirmSelectOperation)
            testOperationList.add("")
        }

        return testOperationList;
    }
}