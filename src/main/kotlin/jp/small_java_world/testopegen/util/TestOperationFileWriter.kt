package jp.small_java_world.testopegen.util

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


class TestOperationFileWriter {
    companion object {
        private const val SPACE_STRING = "        "

        fun writeFile(
            classTemplateFileName: String,
            testClassName: String,
            previousActionStringList: List<String>,
            testOperationList: MutableList<String>,
            testButtonOperationCollectionList: MutableList<MutableList<String>>
        ) {
            val dateModel = TemplateDataModelUtil.generateDataModel(
                testClassName,
                previousActionStringList,
                testOperationList,
                testButtonOperationCollectionList
            )

            //操作を含んだファイルの出力先を確認し、存在しなければ作成
            val outputDirPath =
                Paths.get(".${File.separator}src${File.separator}test${File.separator}kotlin${File.separator}output")
            if (!Files.exists(outputDirPath)) {
                Files.createDirectory(outputDirPath)
            }

            //テンプレートの読み込み
            val template = TemplateFactory.create(classTemplateFileName)

            //ファイル出力
            File("${outputDirPath}${File.separator}$testClassName.kt").bufferedWriter()
                .use { bufferedWriter ->
                    template.process(dateModel, bufferedWriter)
                }
        }
    }

}