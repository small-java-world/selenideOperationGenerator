package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.Selenide
import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil
import org.openqa.selenium.By

class SelectOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.SELECT
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        val options = Selenide.`$$`(By.cssSelector("$cssSelector > option"))

        testOperationList.add("//SELECTボックスの選択")
        for (option in options) {
            SelenideUtil.selectOptionByValueByCssSelector(cssSelector!!, option.value!!)
            SelenideUtil.selectOptionByCssSelector(cssSelector!!, option.text())
            SelenideUtil.shouldBeValueByCssSelector(cssSelector!!, option.value!!)

            val selectOptionByValueOperation =
                CommonDef.SELECT_OPTION_BY_VALUE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector!!)
                    .replace(CommonDef.INPUT_VALUE, option.value!!)
            testOperationList.add(selectOptionByValueOperation)
            testOperationList.add("")

            val selectOptionOperation =
                CommonDef.SELECT_SELECT_OPTION_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector!!)
                    .replace(CommonDef.INPUT_VALUE, option.text())
            testOperationList.add(selectOptionOperation)
            testOperationList.add("")

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