package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.WebDriverRunner
import com.codeborne.selenide.ex.InvalidStateException
import com.codeborne.selenide.ex.UIAssertionError
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import jp.small_java_world.testopegen.TestBase
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil
import jp.small_java_world.testopegen.util.generateRandomLetterOrDigit
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class RadioOperationGeneratorTest : OperationGeneratorTestBase() {
    companion object {
        val EXPECTED_FILENAME_PREFIX = TargetElementType.INPUT_RADIO.type
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
        return RadioOperationGenerator()
    }

    enum class RadioOperationGeneratorTestType(val id: Int, val resultFileName: String) {
        ConfirmExistenceFail(100, "confirmFail.txt"),
        Success(300, "$EXPECTED_FILENAME_PREFIX-successResult.txt"),
    }

    @ParameterizedTest
    @EnumSource(RadioOperationGeneratorTestType::class)
    fun testOperationGenerator(testType: RadioOperationGeneratorTestType) {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns (testType != RadioOperationGeneratorTestType.ConfirmExistenceFail)

        var getNameByCssSelectorTimes = 0

        val radioName = "radio-name"
        val radioValue = "radio-value"

        if (testType.id > RadioOperationGeneratorTestType.ConfirmExistenceFail.id) {
            getNameByCssSelectorTimes = 1
            every { SelenideUtil.getNameByCssSelector(cssSelector) } returns radioName
            every { SelenideUtil.getValueByCssSelector(cssSelector) } returns radioValue
            every { SelenideUtil.selectRadioByCssSelector("input[name=$radioName]", radioValue) } returns Unit
            every { SelenideUtil.shouldBeSelectedByCssSelector(cssSelector) } returns Unit
        }

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals(testType.resultFileName, result)

        verify(exactly = 1) { SelenideUtil.confirmExistenceByCssSelector(cssSelector) }

        verify(exactly = getNameByCssSelectorTimes) {
            SelenideUtil.getNameByCssSelector(
                cssSelector
            )
        }

        verify(exactly = getNameByCssSelectorTimes) {
            SelenideUtil.getValueByCssSelector(
                cssSelector
            )
        }

        verify(exactly = getNameByCssSelectorTimes) {
            SelenideUtil.selectRadioByCssSelector("input[name=$radioName]", radioValue)
        }

        verify(exactly = getNameByCssSelectorTimes) {
            SelenideUtil.shouldBeSelectedByCssSelector(
                cssSelector
            )
        }
    }
}