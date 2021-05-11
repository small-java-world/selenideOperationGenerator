package jp.small_java_world.testopegen.generator

import io.mockk.mockkObject
import jp.small_java_world.testopegen.TestBase
import jp.small_java_world.testopegen.util.SelenideUtil
import org.junit.jupiter.api.BeforeEach

open abstract class OperationGeneratorTestBase : TestBase() {
    @BeforeEach
    open fun beforeEach() = mockkObject(SelenideUtil)

    @BeforeEach
    abstract fun getTargetOperationGenerator() : OperationGenerator
}