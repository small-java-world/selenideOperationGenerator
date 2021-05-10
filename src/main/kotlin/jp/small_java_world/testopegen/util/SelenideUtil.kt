package jp.small_java_world.testopegen.util

import com.codeborne.selenide.Condition.selected
import com.codeborne.selenide.Condition.value
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

fun selectByName(tagName: String): SelenideElement {
    return Selenide.`$`(By.name(tagName))
}

fun selectByClassName(className: String): SelenideElement {
    return Selenide.`$`(By.className(className))
}

fun selectByCssSelector(cssSelector: String): SelenideElement {
    return Selenide.`$`(By.cssSelector(cssSelector))
}

fun selectById(id: String): SelenideElement {
    return Selenide.`$`(By.id(id))
}

fun clickByCssSelector(cssSelector: String) {
    return selectByCssSelector(cssSelector).click()
}

fun clickByCssSelectorUseJS(cssSelector: String) {
    val driver = WebDriverRunner.getWebDriver()
    val executor = driver as JavascriptExecutor
    val element = selectByCssSelector(cssSelector)
    executor.executeScript("arguments[0].click()", element)
}

fun inputTextByCssSelector(cssSelector: String, text: String) {
    selectByCssSelector(cssSelector).value = text
}

fun shouldBeValueByCssSelector(cssSelector: String, expect: String) {
    selectByCssSelector(cssSelector).shouldBe(value(expect))
}

fun selectRadioByCssSelector(cssSelector: String, value: String) {
    selectByCssSelector(cssSelector).selectRadio(value)
}

fun selectOptionByValueByCssSelector(cssSelector: String, value: String) {
    selectByCssSelector(cssSelector).selectOptionByValue(value)
}

fun selectOptionByCssSelector(cssSelector: String, value: String) {
    selectByCssSelector(cssSelector).selectOption(value)
}

fun checkByCssSelector(cssSelector: String) {
    selectByCssSelector(cssSelector).isSelected = true
}

fun unCheckByCssSelector(cssSelector: String) {
    selectByCssSelector(cssSelector).isSelected = false
}

fun shouldBeSelectedByCssSelector(cssSelector: String) {
    selectByCssSelector(cssSelector).shouldBe(selected)
}

fun shouldBeNotSelectedByCssSelector(cssSelector: String) {
    selectByCssSelector(cssSelector).shouldNotBe(selected)
}

fun confirmExistenceByCssSelector(cssSelector: String): Boolean {
    return try {
        Selenide.`$$`(By.cssSelector(cssSelector)).shouldHaveSize(1)
        true;
    } catch (e: Throwable) {
        false;
    }
}

fun isDuplicateByCssSelector(cssSelector: String): Boolean {
    return Selenide.`$$`(By.cssSelector(cssSelector)).size != 1
}

