package jp.small_java_world.testopegen

import com.codeborne.selenide.WebDriverRunner
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import org.jsoup.select.Elements

class HtmlDocumentParser {
    companion object {
        @JvmStatic
        fun getElements(previousAction: () -> Unit, targetTagList: List<String>): Elements {
            // { Selenide.open("file:///C:/example/input.html") }のような、Selenideで生成対象ページを開く処理の
            // 前処理の無名関数の呼び出し　
            previousAction.invoke()

            //Selenideで生成対象ページのhtmlを取得
            val driver = WebDriverRunner.getWebDriver()
            val html = driver.pageSource

            //Jsoupでhtmlを解析
            val htmlDocument = Jsoup.parse(html, "", Parser.htmlParser())

            var result = Elements()

            for (targetTag in targetTagList) {
                //タグ(targetTag)のorg.jsoup.select.Elementsを取得しresultに追加
                result.addAll(htmlDocument.getElementsByTag(targetTag))
            }

            return result
        }
    }
}