package jp.small_java_world.testopegen.util

class TemplateDataModelUtil {
    companion object {
        private const val SPACE_STRING = "        "

        fun generateDataModel(
            testClassName: String,
            previousActionStringList: List<String>,
            testOperationList: MutableList<String>,
            testButtonOperationCollectionList: MutableList<MutableList<String>>
        ): MutableMap<String, Any> {
            val dataModel = mutableMapOf<String, Any>()
            dataModel["testClassName"] = testClassName

            val previousActionBuilder = StringBuilder()
            previousActionStringList.forEach { previousActionBuilder.appendLine(it) }
            dataModel["previousAction"] = previousActionBuilder.toString()

            val testMethodDataMapList = mutableListOf<Map<String, String>>()
            dataModel["testMethodDataList"] = testMethodDataMapList

            for ((index, testButtonOperations) in testButtonOperationCollectionList.withIndex()) {
                //各ボタン要素に対する操作をtestMethodDataMapListに追加
                val testMethodDataMap = generateTestMethodDataMap("button$index test operation", testButtonOperations)
                if (testMethodDataMap.isNotEmpty()) {
                    testMethodDataMapList.add(testMethodDataMap)
                }
            }

            //各ボタン以外の要素に対する操作をtestMethodDataMapListに追加
            val testMethodDataMap = generateTestMethodDataMap("input test operation", testOperationList)
            if (testMethodDataMap.isNotEmpty()) {
                testMethodDataMapList.add(testMethodDataMap)
            }

            return dataModel
        }

        private fun generateTestMethodDataMap(
            name: String,
            testButtonOperations: MutableList<String>
        ): Map<String, String> {
            val operationBuilder = StringBuilder()
            val testMethodDataMap = mutableMapOf<String, String>()
            testMethodDataMap["name"] = name

            for (testButtonOperation in testButtonOperations) {
                operationBuilder.appendLine("$SPACE_STRING$testButtonOperation")
            }

            if (operationBuilder.isNotEmpty()) {
                testMethodDataMap["operation"] = operationBuilder.toString().substring(SPACE_STRING.length)
            }
            return testMethodDataMap
        }
    }
}