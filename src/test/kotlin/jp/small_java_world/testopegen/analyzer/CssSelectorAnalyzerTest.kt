package jp.small_java_world.testopegen.analyzer

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import jp.small_java_world.testopegen.define.CommonDef.Companion.PROJECT_ROOT_PATH
import jp.small_java_world.testopegen.define.TargetElementType
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class CssSelectorAnalyzerTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            Configuration.browser = WebDriverRunner.CHROME;
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            WebDriverRunner.closeWebDriver()
        }
    }

    @Test
    fun `test getCssSelectorElementTypePair input button`() {
        val expectedResultList =
            listOf(
                "#addButton" to TargetElementType.INPUT_BUTTON,
                "#deleteButton" to TargetElementType.INPUT_BUTTON,
                "#delayDeleteButton" to TargetElementType.INPUT_BUTTON,
                "#notDisplayButton" to TargetElementType.INPUT_BUTTON
            )

        testGetCssSelectorElementTypePairCommon("input_button.html", expectedResultList)
    }

    private fun testGetCssSelectorElementTypePairCommon(
        htmlFileName: String,
        expectedResultList: List<Pair<String, TargetElementType>>
    ) {
        val inputTagElements = openAndGetElements(htmlFileName)
        assertEquals(expectedResultList.size, inputTagElements.size)

        val cssSelectorAnalyzer = CssSelectorAnalyzer()
        for ((index, inputTagElement) in inputTagElements.withIndex()) {
            assertEquals(expectedResultList[index], cssSelectorAnalyzer.getCssSelectorElementTypePair(inputTagElement))
        }
    }

    private fun openAndGetElements(htmlFileName: String): List<Element> {
        Selenide.open("file:///$PROJECT_ROOT_PATH/html/$htmlFileName")
        val driver = WebDriverRunner.getWebDriver()
        val html = driver.pageSource
        val doc = Jsoup.parse(html, "", Parser.htmlParser())
        return doc.getElementsByTag("input") + doc.getElementsByTag("select")
    }

    @Test
    fun `test getCssSelectorElementTypePair input radio`() {
        val expectedResultList =
            listOf(
                "#grade1" to TargetElementType.INPUT_RADIO,
                "#grade2" to TargetElementType.INPUT_RADIO
            )

        testGetCssSelectorElementTypePairCommon("input_radio.html", expectedResultList)
    }

    @Test
    fun `test getCssSelectorElementTypePair input checkbox`() {
        val expectedResultList =
            listOf(
                "#lang1" to TargetElementType.INPUT_CHECKBOX,
                "#lang2" to TargetElementType.INPUT_CHECKBOX,
            )

        testGetCssSelectorElementTypePairCommon("input_checkbox.html", expectedResultList)
    }

    @Test
    fun `test getCssSelectorElementTypePair input text`() {
        val expectedResultList =
            listOf(
                "#p-input1-2-1 > #input1" to TargetElementType.INPUT_TEXT,
                "#p-input1-2-2 > #input1" to TargetElementType.INPUT_TEXT,
                "input[name='input1-1']" to TargetElementType.INPUT_TEXT,
                "input[hoge='1']" to TargetElementType.INPUT_TEXT,
                "input[hoge='2']" to TargetElementType.INPUT_TEXT,
                "html > body > input[hoge='3']" to TargetElementType.INPUT_TEXT
            )

        testGetCssSelectorElementTypePairCommon("input_text.html", expectedResultList)
    }

    @Test
    fun `test getCssSelectorElementTypePair select`() {
        val expectedResultList =
            listOf(
                "html > body > select" to TargetElementType.SELECT
            )

        testGetCssSelectorElementTypePairCommon("select.html", expectedResultList)
    }
}