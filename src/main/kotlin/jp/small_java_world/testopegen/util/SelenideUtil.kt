package jp.small_java_world.testopegen.util

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

class SelenideUtil {
    companion object {
        @JvmStatic
        fun selectByName(tagName: String): SelenideElement {
            return Selenide.`$`(By.name(tagName))
        }

        @JvmStatic
        fun selectByClassName(className: String): SelenideElement {
            return Selenide.`$`(By.className(className))
        }

        @JvmStatic
        fun selectByCssSelector(cssSelector: String): SelenideElement {
            return Selenide.`$`(By.cssSelector(cssSelector))
        }

        @JvmStatic
        fun selectListByCssSelector(cssSelector: String): ElementsCollection {
            return Selenide.`$$`(By.cssSelector(cssSelector))
        }

        @JvmStatic
        fun selectById(id: String): SelenideElement {
            return Selenide.`$`(By.id(id))
        }

        @JvmStatic
        fun clickByCssSelector(cssSelector: String) {
            selectByCssSelector(cssSelector).click()
        }

        @JvmStatic
        fun clickByCssSelectorUseJS(cssSelector: String) {
            val driver = WebDriverRunner.getWebDriver()
            val executor = driver as JavascriptExecutor
            val element = selectByCssSelector(cssSelector)
            executor.executeScript("arguments[0].click()", element)
        }

        @JvmStatic
        fun inputTextByCssSelector(cssSelector: String, text: String) {
            selectByCssSelector(cssSelector).value = text
        }

        @JvmStatic
        fun shouldBeValueByCssSelector(cssSelector: String, expect: String) {
            selectByCssSelector(cssSelector).shouldBe(value(expect))
        }

        @JvmStatic
        fun selectRadioByCssSelector(cssSelector: String, value: String) {
            selectByCssSelector(cssSelector).selectRadio(value)
        }

        @JvmStatic
        fun selectOptionByValueByCssSelector(cssSelector: String, value: String) {
            selectByCssSelector(cssSelector).selectOptionByValue(value)
        }

        @JvmStatic
        fun selectOptionByCssSelector(cssSelector: String, value: String) {
            selectByCssSelector(cssSelector).selectOption(value)
        }

        @JvmStatic
        fun checkByCssSelector(cssSelector: String) {
            selectByCssSelector(cssSelector).isSelected = true
        }

        @JvmStatic
        fun unCheckByCssSelector(cssSelector: String) {
            selectByCssSelector(cssSelector).isSelected = false
        }

        @JvmStatic
        fun shouldBeSelectedByCssSelector(cssSelector: String) {
            selectByCssSelector(cssSelector).shouldBe(selected)
        }

        @JvmStatic
        fun shouldBeNotSelectedByCssSelector(cssSelector: String) {
            selectByCssSelector(cssSelector).shouldNotBe(selected)
        }

        @JvmStatic
        fun shouldHaveAttributeByCssSelector(cssSelector: String, attrName: String, attrValue: String) {
            selectByCssSelector(cssSelector).shouldHave(attribute(attrName, attrValue))
        }

        @JvmStatic
        fun confirmExistenceByCssSelector(cssSelector: String): Boolean {
            return try {
                Selenide.`$$`(By.cssSelector(cssSelector)).shouldHaveSize(1)
                true;
            } catch (e: Throwable) {
                false;
            }
        }

        @JvmStatic
        fun isDuplicateByCssSelector(cssSelector: String): Boolean {
            return Selenide.`$$`(By.cssSelector(cssSelector)).size !in (listOf(0, 1))
        }

        @JvmStatic
        fun getValueByCssSelector(cssSelector: String): String? {
            return selectByCssSelector(cssSelector).value
        }

        @JvmStatic
        fun getNameByCssSelector(cssSelector: String): String? {
            return selectByCssSelector(cssSelector).getAttribute("name")
        }
    }
}