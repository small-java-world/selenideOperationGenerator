package jp.small_java_world.testopegen

import com.codeborne.selenide.WebDriverRunner
import jp.small_java_world.testopegen.analyzer.CssSelectorAnalyzer
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.generator.OperationGeneratorFactory
import jp.small_java_world.testopegen.util.TestOperationFileWriter
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class TestExampleOperationGenerator {
    private val logger: Logger = LoggerFactory.getLogger(TestExampleOperationGenerator::class.java)

    fun generate(
        classTemplateFileName: String,
        testClassName: String,
        previousActionStringList: List<String>,
        previousAction: () -> Unit
    ) {
        // { Selenide.open("file:///C:/example/input.html") }のような、Selenideで生成対象ページを開く処理の
        // 前処理の無名関数の呼び出し　
        previousAction.invoke()

        //Selenideで生成対象ページのhtmlを取得
        val driver = WebDriverRunner.getWebDriver()
        val html = driver.pageSource

        //Jsoupでhtmlを解析
        val htmlDocument = Jsoup.parse(html, "", Parser.htmlParser())

        //inputタグのorg.jsoup.select.Elementsを取得
        val inputTagElements = htmlDocument.getElementsByTag("input")
        //selectタグのorg.jsoup.select.Elementsを取得
        val selectTagElements = htmlDocument.getElementsByTag("select")

        //ボタンのテスト操作を格納 ボタン押すと画面遷移する可能性があるので、ボタンは1要素(HTMLの)につき1テストメソッドとするので別に管理
        var testButtonOperationCollectionList = mutableListOf<MutableList<String>>()

        //ボタン以外のテスト操作を格納
        var testOperationList = mutableListOf<String>()

        val cssSelectorAnalyzer = CssSelectorAnalyzer()

        //各inputタグを処理していく
        for (inputTagElement in inputTagElements + selectTagElements) {
            // 現在の処理対象のinputTagElementに対応する Pair<String?, TargetElementType?>を取得
            var selectorElementTypePair = cssSelectorAnalyzer.getCssSelectorElementTypePair(inputTagElement)
            var operationGenerator = OperationGeneratorFactory.getOperationGenerator(selectorElementTypePair.second)

            if (operationGenerator != null) {
                when (selectorElementTypePair.second) {
                    TargetElementType.INPUT_BUTTON -> testButtonOperationCollectionList.add(
                        operationGenerator.generateOperation(
                            selectorElementTypePair.first
                        )
                    )
                    else -> testOperationList.addAll(operationGenerator.generateOperation(selectorElementTypePair.first))
                }
            }
        }

        TestOperationFileWriter.writeFile(
            classTemplateFileName,
            testClassName,
            previousActionStringList,
            testOperationList,
            testButtonOperationCollectionList
        )

    }


}