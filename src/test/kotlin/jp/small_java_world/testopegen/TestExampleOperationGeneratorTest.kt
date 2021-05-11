package jp.small_java_world.testopegen

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import jp.small_java_world.testopegen.analyzer.CssSelectorAnalyzer
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.generator.ButtonOperationGenerator
import jp.small_java_world.testopegen.generator.OperationGeneratorFactory
import jp.small_java_world.testopegen.generator.TextOperationGenerator
import jp.small_java_world.testopegen.util.TestOperationFileWriter
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestExampleOperationGeneratorTest : TestBase() {
    var testExampleOperationGenerator = TestExampleOperationGenerator()
    lateinit var cssSelectorAnalyzer: CssSelectorAnalyzer

    @BeforeEach
    fun beforeEach() {
        mockkObject(HtmlDocumentParser)
        mockkObject(OperationGeneratorFactory)
        mockkObject(TestOperationFileWriter)

        cssSelectorAnalyzer = mockk<CssSelectorAnalyzer>()
        testExampleOperationGenerator.cssSelectorAnalyzer = cssSelectorAnalyzer
    }

    @AfterEach
    override fun afterEach() = super.afterEach()

    @Test
    fun testGenerate() {
        val buttonElement = Element("button")
        val textElement = Element("input")
        val getElementsResult = Elements(listOf(buttonElement, textElement))

        val previousAction = {}
        every { HtmlDocumentParser.getElements(previousAction, listOf("input", "select")) } returns getElementsResult

        val buttonPair = "buttonCss" to TargetElementType.INPUT_BUTTON
        val textPair = "textCss" to TargetElementType.INPUT_TEXT

        val buttonOperationGenerator = mockk<ButtonOperationGenerator>()
        val textOperationGenerator = mockk<TextOperationGenerator>()

        every { cssSelectorAnalyzer.getCssSelectorElementTypePair(buttonElement) } returns buttonPair
        every { cssSelectorAnalyzer.getCssSelectorElementTypePair(textElement) } returns textPair

        every { OperationGeneratorFactory.getOperationGenerator(buttonPair.second) } returns buttonOperationGenerator
        every { OperationGeneratorFactory.getOperationGenerator(textPair.second) } returns textOperationGenerator

        val buttonGenerateOperationResult = mutableListOf("buttonResult")
        val textGenerateOperationResult = mutableListOf("textResult")
        every { buttonOperationGenerator.generateOperation(buttonPair.first) } returns buttonGenerateOperationResult
        every { textOperationGenerator.generateOperation(textPair.first) } returns textGenerateOperationResult

        val classTemplateFileName = "classTemplateFileName"
        val testClassName = "testClassName"
        val previousActionStringList = listOf("previousActionStringListContent")

        every {
            TestOperationFileWriter.writeFile(
                classTemplateFileName,
                testClassName,
                previousActionStringList,
                textGenerateOperationResult,
                mutableListOf(buttonGenerateOperationResult)
            )
        } returns Unit

        testExampleOperationGenerator.generate(
            classTemplateFileName,
            testClassName,
            previousActionStringList,
            previousAction
        )

        verify(exactly = 1) {
            HtmlDocumentParser.getElements(previousAction, listOf("input", "select"))
            cssSelectorAnalyzer.getCssSelectorElementTypePair(buttonElement)
            cssSelectorAnalyzer.getCssSelectorElementTypePair(textElement)
            OperationGeneratorFactory.getOperationGenerator(buttonPair.second)
            OperationGeneratorFactory.getOperationGenerator(textPair.second)
            buttonOperationGenerator.generateOperation(buttonPair.first)
            textOperationGenerator.generateOperation(textPair.first)

            TestOperationFileWriter.writeFile(
                classTemplateFileName,
                testClassName,
                previousActionStringList,
                textGenerateOperationResult,
                mutableListOf(buttonGenerateOperationResult)
            )
        }
    }

}