package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.ex.InvalidStateException
import com.codeborne.selenide.ex.UIAssertionError
import jp.small_java_world.testopegen.define.CommonDef.Companion.CLICK_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.CLICK_USE_JS_TEMPLATE
import jp.small_java_world.testopegen.define.CommonDef.Companion.TARGET_CSS_SELECTOR
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.clickByCssSelector

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
            clickByCssSelector(cssSelector!!)
        } catch (e: Throwable) {
            when (e) {
                //UIAssertionError:非表示の要素をクリック、InvalidStateException他の要素によりクリックがブロック
                is UIAssertionError, is InvalidStateException -> {
                    testOperationList.add("// clickByCssSelectorでは押せませんでした!!")
                    usingJavaScript = true
                }
                else -> throw e
            }
        }

        val clickOperation = CLICK_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector!!)
        testOperationList.add(if (usingJavaScript) "//$clickOperation" else clickOperation)
        testOperationList.add("")

        if (usingJavaScript) {
            val clickUseJsOperation = CLICK_USE_JS_TEMPLATE.replace(TARGET_CSS_SELECTOR, cssSelector)
            testOperationList.add(clickUseJsOperation)
            testOperationList.add("")
        }

        return testOperationList;
    }

}