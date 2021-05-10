package jp.small_java_world.testopegen.generator

import jp.small_java_world.testopegen.define.TargetElementType

class OperationGeneratorFactory {
    companion object {
        fun getOperationGenerator(elementType: TargetElementType?): OperationGenerator? {
            return when (elementType) {
                TargetElementType.INPUT_BUTTON -> ButtonOperationGenerator()
                TargetElementType.INPUT_TEXT -> TextOperationGenerator()
                TargetElementType.INPUT_RADIO -> RadioOperationGenerator()
                TargetElementType.INPUT_CHECKBOX -> CheckOperationGenerator()
                TargetElementType.SELECT -> SelectOperationGenerator()
                else -> null
            }
        }

    }

}