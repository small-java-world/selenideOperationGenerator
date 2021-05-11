package jp.small_java_world.testopegen.generator

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil
import jp.small_java_world.testopegen.util.generateRandomLetterOrDigit
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class TextOperationGeneratorTest : OperationGeneratorTestBase() {
    companion object {
        val EXPECTED_FILENAME_PREFIX = TargetElementType.INPUT_TEXT.type
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
        return TextOperationGenerator()
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

        val dummyStr = "dummy"
        mockkStatic("jp.small_java_world.testopegen.util.TestUtilKt")
        every { generateRandomLetterOrDigit(any<Int>()) } returns dummyStr

        var inputTextByCssSelectorTimes = 0
        if (testType.id > RadioOperationGeneratorTestType.ConfirmExistenceFail.id) {
            inputTextByCssSelectorTimes = 1
            every { SelenideUtil.inputTextByCssSelector(cssSelector, dummyStr) } returns Unit
            every { SelenideUtil.shouldBeValueByCssSelector(cssSelector, dummyStr) } returns Unit
        }

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals(testType.resultFileName, result)

        verify(exactly = 1) { SelenideUtil.confirmExistenceByCssSelector(cssSelector) }

        verify(exactly = inputTextByCssSelectorTimes) {
            generateRandomLetterOrDigit(any<Int>())
        }

        verify(exactly = inputTextByCssSelectorTimes) {
            SelenideUtil.inputTextByCssSelector(cssSelector, dummyStr)
        }

        verify(exactly = inputTextByCssSelectorTimes) {
            SelenideUtil.shouldBeValueByCssSelector(cssSelector, dummyStr)
        }
    }
}