package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.Selenide
import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.*
import org.openqa.selenium.By

class CheckOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_CHECKBOX
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        testOperationList.add("//チェックボックスのチェック")
        SelenideUtil.checkByCssSelector(cssSelector!!)

        val checkOperation = CommonDef.CHECK_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(checkOperation)
        testOperationList.add("")

        testOperationList.add("//チェックされたことの検証")
        SelenideUtil.shouldBeSelectedByCssSelector(cssSelector!!)

        val confirmOperation = CommonDef.CONFIRM_SELECTED_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        testOperationList.add("//チェックボックスのアンチェック")
        SelenideUtil.unCheckByCssSelector(cssSelector)

        val unCheckOperation = CommonDef.UNCHECK_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(unCheckOperation)
        testOperationList.add("")

        testOperationList.add("//チェックさていないことの検証")
        SelenideUtil.shouldBeNotSelectedByCssSelector(cssSelector)

        val confirmUnCheckOperation =
            CommonDef.CONFIRM_NOT_SELECTED_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
        testOperationList.add(confirmUnCheckOperation)
        testOperationList.add("")

        return testOperationList;
    }
}