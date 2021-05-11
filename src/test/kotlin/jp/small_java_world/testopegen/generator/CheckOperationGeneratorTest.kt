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
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CheckOperationGeneratorTest : OperationGeneratorTestBase() {
    companion object {
        val EXPECTED_FILENAME_PREFIX = TargetElementType.INPUT_CHECKBOX.type
    }

    @BeforeEach
    override fun beforeEach() {
        super.beforeEach()
    }

    override fun getTargetOperationGenerator(): OperationGenerator {
        return CheckOperationGenerator()
    }

    enum class CheckOperationGeneratorTestType(val id: Int, val resultFileName: String) {
        ConfirmExistenceFail(100, "confirmFail.txt"),
        Success(300, "$EXPECTED_FILENAME_PREFIX-successResult.txt"),
    }

    @ParameterizedTest
    @EnumSource(CheckOperationGeneratorTestType::class)
    fun testOperationGenerator(testType: CheckOperationGeneratorTestType) {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns (testType != CheckOperationGeneratorTestType.ConfirmExistenceFail)

        var checkByCssSelectorTimes = 0

        if (testType.id > CheckOperationGeneratorTestType.ConfirmExistenceFail.id) {
            checkByCssSelectorTimes = 1
            every { SelenideUtil.checkByCssSelector(cssSelector) } returns Unit
            every { SelenideUtil.shouldBeSelectedByCssSelector(cssSelector) } returns Unit
            every { SelenideUtil.unCheckByCssSelector(cssSelector) } returns Unit
            every { SelenideUtil.shouldBeNotSelectedByCssSelector(cssSelector) } returns Unit
        }

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals(testType.resultFileName, result)

        verify(exactly = 1) { SelenideUtil.confirmExistenceByCssSelector(cssSelector) }

        verify(exactly = checkByCssSelectorTimes) {
            SelenideUtil.checkByCssSelector(
                cssSelector
            )
        }

        verify(exactly = checkByCssSelectorTimes) {
            SelenideUtil.shouldBeSelectedByCssSelector(
                cssSelector
            )
        }

        verify(exactly = checkByCssSelectorTimes) {
            SelenideUtil.unCheckByCssSelector(
                cssSelector
            )
        }

        verify(exactly = checkByCssSelectorTimes) {
            SelenideUtil.shouldBeNotSelectedByCssSelector(
                cssSelector
            )
        }
    }
}