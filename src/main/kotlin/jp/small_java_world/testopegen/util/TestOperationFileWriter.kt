package jp.small_java_world.testopegen.util

import freemarker.template.Configuration
import java.io.File
import java.io.StringWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


class TestOperationFileWriter {
    companion object {
        const val SPACE_STRING = "        "

        fun writeFile(
            classTemplateFileName: String,
            testClassName: String,
            previousActionStringList: List<String>,
            testOperationList: MutableList<String>,
            testButtonOperationCollectionList: MutableList<MutableList<String>>
        ) {
            val configuration = Configuration(Configuration.VERSION_2_3_23)
            configuration.setDirectoryForTemplateLoading(File("src/main/resources/template"))
            val template = configuration.getTemplate(classTemplateFileName)

            val templateMap = mutableMapOf<String, Any>()
            templateMap["testClassName"] = testClassName

            val previousActionBuilder = StringBuilder()
            previousActionStringList.forEach { previousActionBuilder.appendLine(it) }
            templateMap["previousAction"] = previousActionBuilder.toString()

            val testMethodDataMapList = mutableListOf<Map<String, String>>()
            templateMap["testMethodDataList"] = testMethodDataMapList

            for ((index, testButtonOperations) in testButtonOperationCollectionList.withIndex()) {
                val testMethodDataMap = generateTestMethodDataMap("button$index test operation", testButtonOperations)
                if (testMethodDataMap.isNotEmpty()) {
                    testMethodDataMapList.add(testMethodDataMap)
                }
            }


            val testMethodDataMap = generateTestMethodDataMap("input test operation", testOperationList)
            if (testMethodDataMap.isNotEmpty()) {
                testMethodDataMapList.add(testMethodDataMap)
            }

            val outputDirPath =
                Paths.get(".${File.separator}src${File.separator}test${File.separator}kotlin${File.separator}output")
            if (!Files.exists(outputDirPath)) {
                Files.createDirectory(outputDirPath)
            }

            File("${outputDirPath}${File.separator}$testClassName.kt").bufferedWriter()
                .use { bufferedWriter ->
                    template.process(templateMap, bufferedWriter)
                }
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