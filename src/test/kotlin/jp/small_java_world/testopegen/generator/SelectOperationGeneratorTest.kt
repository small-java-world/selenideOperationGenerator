package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.impl.StaticDriver
import com.codeborne.selenide.impl.WebElementsCollectionWrapper
import io.mockk.every
import io.mockk.verify
import jp.small_java_world.testopegen.element.DummySelenideElement
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SelectOperationGeneratorTest : OperationGeneratorTestBase() {
    @BeforeEach
    override fun beforeEach() {
        super.beforeEach()
    }

    @AfterEach
    override fun afterEach() {
        super.afterEach()
    }

    override fun getTargetOperationGenerator(): OperationGenerator {
        return SelectOperationGenerator()
    }

    @Test
    fun testOperationGenerator() {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns true

        val value1 = "value1"
        val value2 = "value2"
        val text1 = "text1"
        val text2 = "text2"

        var elementsCollectionValue = mutableListOf(DummySelenideElement(), DummySelenideElement())
        elementsCollectionValue[0].textValue = text1
        elementsCollectionValue[0].valueValue = value1
        elementsCollectionValue[1].textValue = text2
        elementsCollectionValue[1].valueValue = value2

        val optionCollection =
            WebElementsCollectionWrapper(StaticDriver(), elementsCollectionValue)

        val optionList = ElementsCollection(optionCollection)
        every { SelenideUtil.selectListByCssSelector("$cssSelector > option") } returns optionList
        every { SelenideUtil.selectOptionByValueByCssSelector(cssSelector, value1) } returns Unit
        every { SelenideUtil.selectOptionByCssSelector(cssSelector, text1) } returns Unit
        every { SelenideUtil.shouldBeValueByCssSelector(cssSelector, value1) } returns Unit
        every { SelenideUtil.selectOptionByValueByCssSelector(cssSelector, value2) } returns Unit
        every { SelenideUtil.selectOptionByCssSelector(cssSelector, text2) } returns Unit
        every { SelenideUtil.shouldBeValueByCssSelector(cssSelector, value2) } returns Unit

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals("select-successResult.txt", result)

        verify(exactly = 1) {
            SelenideUtil.confirmExistenceByCssSelector(cssSelector)
        }

        for (testDataPair in listOf(Pair(value1, text1), Pair(value2, text2))) {
            verify(exactly = 1) {
                SelenideUtil.confirmExistenceByCssSelector(cssSelector)
                SelenideUtil.selectListByCssSelector("$cssSelector > option")
                SelenideUtil.selectOptionByValueByCssSelector(cssSelector, testDataPair.first)
                SelenideUtil.selectOptionByCssSelector(cssSelector, testDataPair.second)
                SelenideUtil.shouldBeValueByCssSelector(cssSelector, testDataPair.first)
            }
        }
    }
}