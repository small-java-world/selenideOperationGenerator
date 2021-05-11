package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil

interface OperationGenerator {

    fun generateCustomOperation(cssSelector: String?, testOperationList: MutableList<String>): Collection<String>
    fun getElementType(): TargetElementType

    fun generateOperation(cssSelector: String?): MutableList<String> {
        var testOperationList = mutableListOf<String>()
        if (addConfirmOperation(cssSelector, getElementType().tagNameJp, testOperationList)) {
            generateCustomOperation(cssSelector, testOperationList)
        }

        return testOperationList;
    }

    fun addConfirmOperation(
        cssSelector: String?,
        elementName: String,
        testOperationList: MutableList<String>
    ): Boolean {
        testOperationList.add("/**************** cssSelector ${cssSelector} の処理 start ****************/")

        if (!SelenideUtil.confirmExistenceByCssSelector(cssSelector!!)) {
            testOperationList.add("//confirmExistenceByCssSelector fail")
            return false
        }

        val confirmOperation =
            CommonDef.CONFIRM_EXISTENCE_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector!!)
        testOperationList.add("//${elementName}の存在確認")
        testOperationList.add(confirmOperation)
        testOperationList.add("")

        return true
    }
}