package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.TargetElementType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.reflect.KClass

class OperationGeneratorFactoryTest {

    @ParameterizedTest
    @EnumSource(TargetElementType::class)
    fun testGetOperationGenerator(targetElementType: TargetElementType) {
        var result = OperationGeneratorFactory.getOperationGenerator(targetElementType)

        when (targetElementType) {
            TargetElementType.INPUT_BUTTON -> assertTrue(result is ButtonOperationGenerator)
            TargetElementType.INPUT_TEXT -> assertTrue(result is TextOperationGenerator)
            TargetElementType.INPUT_RADIO -> assertTrue(result is RadioOperationGenerator)
            TargetElementType.INPUT_CHECKBOX -> assertTrue(result is CheckOperationGenerator)
            TargetElementType.SELECT -> assertTrue(result is SelectOperationGenerator)
            else -> fail()
        }
    }
}