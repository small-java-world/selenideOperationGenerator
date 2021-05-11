package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.impl.StaticDriver
import com.codeborne.selenide.impl.WebElementsCollectionWrapper
import io.mockk.every
import io.mockk.verify
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.element.DummySelenideElement
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class SelectOperationGeneratorTest : OperationGeneratorTestBase() {
    companion object {
        val EXPECTED_FILENAME_PREFIX = TargetElementType.SELECT.type
    }

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

    enum class SelectOperationGeneratorTestType(val id: Int, val resultFileName: String) {
        ConfirmExistenceFail(100, "confirmFail.txt"),
        Success(300, "$EXPECTED_FILENAME_PREFIX-successResult.txt"),
    }

    @ParameterizedTest
    @EnumSource(SelectOperationGeneratorTestType::class)
    fun testOperationGenerator(testType: SelectOperationGeneratorTestType) {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns (testType != SelectOperationGeneratorTestType.ConfirmExistenceFail)

        var selectListByCssSelectorTimes = 0

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
        if (testType.id > SelectOperationGeneratorTestType.ConfirmExistenceFail.id) {
            selectListByCssSelectorTimes = 1
            every { SelenideUtil.selectListByCssSelector("$cssSelector > option") } returns optionList

            every { SelenideUtil.selectOptionByValueByCssSelector(cssSelector, value1) } returns Unit
            every { SelenideUtil.selectOptionByCssSelector(cssSelector, text1) } returns Unit
            every { SelenideUtil.shouldBeValueByCssSelector(cssSelector, value1) } returns Unit

            every { SelenideUtil.selectOptionByValueByCssSelector(cssSelector, value2) } returns Unit
            every { SelenideUtil.selectOptionByCssSelector(cssSelector, text2) } returns Unit
            every { SelenideUtil.shouldBeValueByCssSelector(cssSelector, value2) } returns Unit
        }

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals(testType.resultFileName, result)

        verify(exactly = 1) { SelenideUtil.confirmExistenceByCssSelector(cssSelector) }

        verify(exactly = selectListByCssSelectorTimes) {
            SelenideUtil.selectListByCssSelector("$cssSelector > option")
        }

        for (testDataPair in listOf(Pair(value1, text1), Pair(value2, text2))) {
            verify(exactly = selectListByCssSelectorTimes) {
                SelenideUtil.selectOptionByValueByCssSelector(cssSelector, testDataPair.first)
            }

            verify(exactly = selectListByCssSelectorTimes) {
                SelenideUtil.selectOptionByCssSelector(cssSelector, testDataPair.second)
            }

            verify(exactly = selectListByCssSelectorTimes) {
                SelenideUtil.shouldBeValueByCssSelector(cssSelector, testDataPair.first)
            }
        }

    }
}