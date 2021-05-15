package jp.small_java_world.testopegen.generator

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OperationGeneratorTest : OperationGeneratorTestBase() {
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
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns false

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals("confirmFail.txt", result)

        verify(exactly = 1) { SelenideUtil.confirmExistenceByCssSelector(cssSelector) }
    }
}