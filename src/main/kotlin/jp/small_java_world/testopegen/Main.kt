package jp.small_java_world.testopegen

import com.codeborne.selenide.Selenide
import jp.small_java_world.testopegen.define.CommonDef.Companion.PROJECT_ROOT_PATH
import jp.small_java_world.testopegen.util.SelenideUtil

fun main(args: Array<String>) {
    val testOperationGenerator = TestExampleOperationGenerator()
    val targetHtmlFullPath = "file://$PROJECT_ROOT_PATH/html/input.html"

    testOperationGenerator.generate(
        "testOperationClassTemplate.ftl",
        "InputTest",
        listOf(
            "Selenide.open(\"$targetHtmlFullPath\")",
            "SelenideUtil.shouldHaveAttributeByCssSelector(\"#notDisplayButton\", \"style\", \"display: none;\")"
        )
    )
    {
        Selenide.open(targetHtmlFullPath)
        SelenideUtil.shouldHaveAttributeByCssSelector("#notDisplayButton", "style", "display: none;")
    }
}
