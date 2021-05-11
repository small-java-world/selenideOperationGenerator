package jp.small_java_world.testopegen.generator

import io.mockk.clearAllMocks
import io.mockk.mockkObject
import jp.small_java_world.testopegen.TestBase
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open abstract class OperationGeneratorTestBase : TestBase() {
    open fun beforeEach() = mockkObject(SelenideUtil)

    override fun afterEach()  = super.afterEach()

    abstract fun getTargetOperationGenerator() : OperationGenerator
}