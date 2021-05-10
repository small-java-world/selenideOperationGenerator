package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.Selenide
import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_SELECTED_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.SELECT_OPTION_BY_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.SELECT_SELECT_OPTION_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.TARGET_CSS_SELECTOR
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.inputTextByCssSelector
import jp.small_java_world.testopegen.util.selectOptionByCssSelector
import jp.small_java_world.testopegen.util.selectOptionByValueByCssSelector
import jp.small_java_world.testopegen.util.shouldBeValueByCssSelector
import org.openqa.selenium.By

class SelectOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.SELECT
    }

    override fun generateCustomOperation(cssSelector: String?, testOperationList : MutableList<String>): Collection<String> {
        val options = Selenide.`$$`(By.cssSelector("$cssSelector > option"))

        testOperationList.add("//SELECTボックスの選択")
        for(option in options) {
            selectOptionByValueByCssSelector(cssSelector!!, option.value!!)
            selectOptionByCssSelector(cssSelector!!, option.text())
            shouldBeValueByCssSelector(cssSelector!!, option.value!!)

            val selectOptionByValueOperation = SELECT_OPTION_BY_VALUE_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector!!).replace(INPUT_VALUE, option.value!!)
            testOperationList.add(selectOptionByValueOperation)
            testOperationList.add("")

            val selectOptionOperation = SELECT_SELECT_OPTION_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector!!).replace(INPUT_VALUE, option.text())
            testOperationList.add(selectOptionOperation)
            testOperationList.add("")

            val confirmSelectOperation = CONFIRM_SELECTED_TEMPLATE.replace(TARGET_CSS_SELECTOR, "$cssSelector > option[value='${option.value!!}']").replace(INPUT_VALUE, option.text())
            testOperationList.add(confirmSelectOperation)
            testOperationList.add("")
        }

        return testOperationList;
    }
}