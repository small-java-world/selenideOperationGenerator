package jp.small_java_world.testopegen.generator

import io.mockk.every
import io.mockk.verify
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CheckOperationGeneratorTest : OperationGeneratorTestBase() {
    @BeforeEach
    override fun beforeEach() {
        super.beforeEach()
    }

    @AfterEach
    override fun afterEach() {
        super.afterEach()
    }

    override fun getTargetOperationGenerator(): OperationGenerator {
        return CheckOperationGenerator()
    }

    @Test
    fun testOperationGenerator() {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns true
        every { SelenideUtil.checkByCssSelector(cssSelector) } returns Unit
        every { SelenideUtil.shouldBeSelectedByCssSelector(cssSelector) } returns Unit
        every { SelenideUtil.unCheckByCssSelector(cssSelector) } returns Unit
        every { SelenideUtil.shouldBeNotSelectedByCssSelector(cssSelector) } returns Unit

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals("checkbox-successResult.txt", result)

        verify(exactly = 1) {
            SelenideUtil.confirmExistenceByCssSelector(cssSelector)
            SelenideUtil.checkByCssSelector(cssSelector)
            SelenideUtil.shouldBeSelectedByCssSelector(cssSelector)
            SelenideUtil.unCheckByCssSelector(cssSelector)
            SelenideUtil.shouldBeNotSelectedByCssSelector(cssSelector)
        }
    }
}