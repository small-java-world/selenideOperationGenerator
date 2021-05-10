package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.Selenide
import jp.small_java_world.testopegen.define.CommonDef.Companion.CHECK_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_NOT_SELECTED_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_SELECTED_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.CONFIRM_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE
import jp.small_java_world.testopegen.define.CommonDef.Companion.INPUT_VALUE_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.TARGET_CSS_SELECTOR
import jp.small_java_world.testopegen.define.CommonDef.Companion.UNCHECK_TEMPLATE
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.*
import org.openqa.selenium.By

class CheckOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_CHECKBOX
    }

    override fun generateCustomOperation(cssSelector: String?, testOperationList : MutableList<String>): Collection<String> {
        testOperationList.add("//チェックボックスのチェック")
        checkByCssSelector(cssSelector!!)

        val checkOperation = CHECK_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(checkOperation)
        testOperationList.add("")

        testOperationList.add("//チェックされたことの検証")
        val confirmOperation = CONFIRM_SELECTED_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        testOperationList.add("//チェックボックスのアンチェック")
        unCheckByCssSelector(cssSelector)

        val unCheckOperation = UNCHECK_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(unCheckOperation)
        testOperationList.add("")

        testOperationList.add("//チェックさていないことの検証")
        shouldBeNotSelectedByCssSelector(cssSelector)

        val confirmUnCheckOperation = CONFIRM_NOT_SELECTED_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(confirmUnCheckOperation)
        testOperationList.add("")

        return testOperationList;
    }
}