package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.ex.UIAssertionError
import jp.small_java_world.testopegen.define.CommonDef
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil

class ButtonOperationGenerator : OperationGenerator {
    override fun getElementType(): TargetElementType {
        return TargetElementType.INPUT_BUTTON
    }

    override fun generateCustomOperation(
        cssSelector: String?,
        testOperationList: MutableList<String>
    ): Collection<String> {
        var usingJavaScript = false

        try {
            SelenideUtil.clickByCssSelector(cssSelector!!)
        } catch (e: Throwable) {
            when (e) {
                //UIAssertionError:非表示の要素をクリック、InvalidStateException他の要素によりクリックがブロック
                is UIAssertionError -> {
                    testOperationList.add("// clickByCssSelector fail")
                    usingJavaScript = true
                }
                else -> throw e
            }
        }

        val clickOperation = CommonDef.CLICK_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector!!)
        testOperationList.add(if (usingJavaScript) "//$clickOperation" else clickOperation)
        testOperationList.add("")

        if (usingJavaScript) {
            val clickUseJsOperation =
                CommonDef.CLICK_USE_JS_TEMPLATE.replace(CommonDef.TARGET_CSS_SELECTOR, cssSelector)
            testOperationList.add(clickUseJsOperation)
            testOperationList.add("")
        }

        return testOperationList;
    }

}