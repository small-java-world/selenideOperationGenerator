package jp.small_java_world.testopegen

import jp.small_java_world.testopegen.analyzer.CssSelectorAnalyzer
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.generator.OperationGeneratorFactory
import jp.small_java_world.testopegen.util.TestOperationFileWriter


class TestExampleOperationGenerator() {
    var cssSelectorAnalyzer = CssSelectorAnalyzer()

    fun generate(
        classTemplateFileName: String,
        testClassName: String,
        previousActionStringList: List<String>,
        previousAction: () -> Unit
    ) {
        //inputタグとselectタグのorg.jsoup.select.Elementsを取得
        val targetElements = HtmlDocumentParser.getElements(previousAction, listOf("input", "select"))

        //ボタンのテスト操作の格納用 ボタン押すと画面遷移する可能性があるので、ボタンは1要素(HTMLの)につき1テストメソッドとするので別に管理
        var testButtonOperationCollectionList = mutableListOf<MutableList<String>>()

        //ボタン以外のテスト操作の格納用
        var testOperationList = mutableListOf<String>()

        //各タグを処理していく
        for (targetElement in targetElements) {

            // 現在の処理対象のinputTagElementに対応する Pair<String?, TargetElementType?>を取得
            var selectorElementTypePair = cssSelectorAnalyzer.getCssSelectorElementTypePair(targetElement)

            //TargetElementTypeに対応するOperationGeneratorを生成
            var operationGenerator = OperationGeneratorFactory.getOperationGenerator(selectorElementTypePair.second)

            if (operationGenerator != null) {
                when (selectorElementTypePair.second) {
                    //TargetElementType.INPUT_BUTTONのときのみtestButtonOperationCollectionListに追加
                    TargetElementType.INPUT_BUTTON -> testButtonOperationCollectionList.add(
                        operationGenerator.generateOperation(
                            selectorElementTypePair.first
                        )
                    )
                    else -> testOperationList.addAll(operationGenerator.generateOperation(selectorElementTypePair.first))
                }
            }
        }

        //ファイル出力
        TestOperationFileWriter.writeFile(
            classTemplateFileName,
            testClassName,
            previousActionStringList,
            testOperationList,
            testButtonOperationCollectionList
        )
    }
}