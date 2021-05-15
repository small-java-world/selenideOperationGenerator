package jp.small_java_world.testopegen.generator

import io.mockk.every
import io.mockk.verify
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RadioOperationGeneratorTest : OperationGeneratorTestBase() {
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

    @Test
    fun testOperationGenerator() {
        val cssSelector = "cssSelectorValue"
        every { SelenideUtil.confirmExistenceByCssSelector(cssSelector) } returns true

        val radioName = "radio-name"
        val radioValue = "radio-value"

        every { SelenideUtil.getNameByCssSelector(cssSelector) } returns radioName
        every { SelenideUtil.getValueByCssSelector(cssSelector) } returns radioValue
        every { SelenideUtil.selectRadioByCssSelector("input[name=$radioName]", radioValue) } returns Unit
        every { SelenideUtil.shouldBeSelectedByCssSelector(cssSelector) } returns Unit

        var result = getTargetOperationGenerator().generateOperation(cssSelector)
        assertFileEquals("radio-successResult.txt", result)

        verify(exactly = 1) {
            SelenideUtil.confirmExistenceByCssSelector(cssSelector)
            SelenideUtil.getNameByCssSelector(cssSelector)
            SelenideUtil.getValueByCssSelector(cssSelector)
            SelenideUtil.selectRadioByCssSelector("input[name=$radioName]", radioValue)
            SelenideUtil.shouldBeSelectedByCssSelector(
                cssSelector
            )
        }
    }
}