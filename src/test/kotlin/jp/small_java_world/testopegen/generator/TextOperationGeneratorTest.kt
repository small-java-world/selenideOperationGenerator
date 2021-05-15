package jp.small_java_world.testopegen.generator

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import jp.small_java_world.testopegen.util.SelenideUtil
import jp.small_java_world.testopegen.util.generateRandomLetterOrDigit
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TextOperationGeneratorTest : OperationGeneratorTestBase() {
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

    @Test
    fun testOperationGenerator() {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns true

        val dummyStr = "dummy"
        mockkStatic("jp.small_java_world.testopegen.util.TestUtilKt")
        every { generateRandomLetterOrDigit(any<Int>()) } returns dummyStr
        every { SelenideUtil.inputTextByCssSelector(cssSelector, dummyStr) } returns Unit
        every { SelenideUtil.shouldBeValueByCssSelector(cssSelector, dummyStr) } returns Unit

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals("text-successResult.txt", result)

        verify(exactly = 1) {
            SelenideUtil.confirmExistenceByCssSelector(cssSelector)
            generateRandomLetterOrDigit(any<Int>())
            SelenideUtil.inputTextByCssSelector(cssSelector, dummyStr)
            SelenideUtil.shouldBeValueByCssSelector(cssSelector, dummyStr)
        }
    }
}