package jp.small_java_world.testopegen.generator

import com.codeborne.selenide.ex.InvalidStateException
import com.codeborne.selenide.ex.UIAssertionError
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import jp.small_java_world.testopegen.TestBase
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class ButtonOperationGeneratorTest : TestBase() {

    @BeforeEach
    fun beforeEach() = mockkObject(SelenideUtil.Companion)

    enum class ButtonOperationGeneratorTestType(val id: Int, val resultFileName: String) {
        ConfirmExistenceFail(100, "confirmFail.txt"),
        UIAssertionError(200, "successJsResult.txt"),
        Success(300, "successResult.txt"),

    }

    @ParameterizedTest
    @EnumSource(ButtonOperationGeneratorTestType::class)
    fun testButtonOperationGenerator(testType: ButtonOperationGeneratorTestType) {
        val cssSelector = "cssSelectorValue"
        var buttonOperationGenerator = ButtonOperationGenerator()

        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns (testType != ButtonOperationGeneratorTestType.ConfirmExistenceFail)

        var clickByCssSelectorTimes = 0

        if (testType.id > ButtonOperationGeneratorTestType.ConfirmExistenceFail.id) {
            clickByCssSelectorTimes = 1
            if (testType == ButtonOperationGeneratorTestType.Success) {
                every { SelenideUtil.clickByCssSelector(cssSelector) } returns Unit
            } else {
                every { SelenideUtil.clickByCssSelector(cssSelector) } throws InvalidStateException(null, "")
            }
        }

        var result = buttonOperationGenerator.generateOperation(cssSelector)
        assertFileEquals(testType.resultFileName, result)

        verify(exactly = 1) { SelenideUtil.confirmExistenceByCssSelector(cssSelector) }
        verify(exactly = clickByCssSelectorTimes) {
            SelenideUtil.clickByCssSelector(
                cssSelector
            )
        }
    }
}