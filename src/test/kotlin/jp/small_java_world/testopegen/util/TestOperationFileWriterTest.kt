package jp.small_java_world.testopegen.util

import freemarker.template.Template
import io.mockk.*
import jp.small_java_world.testopegen.TestBase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.Writer
import java.nio.file.Files
import java.nio.file.Paths

class TestOperationFileWriterTest : TestBase() {

    @BeforeEach
    fun beforeEach() {
        mockkObject(TemplateDataModelUtil)
        mockkObject(TemplateFactory)
        mockkStatic(Files::class)
    }

    override fun afterEach() = super.afterEach()

    @Test
    fun testWriteFile() {
        val classTemplateFileName = "classTemplateFileName"
        val testClassName = "testClassName"
        val previousActionStringList = listOf("previousActionStringListContent")
        val testOperationList = mutableListOf("testOperationListContent")
        val testButtonOperationCollectionList = mutableListOf(mutableListOf("testButtonOperationCollectionListContent"))

        val dateModel: MutableMap<String, Any> = mutableMapOf("dummyKey" to "dummyValue")

        every {
            TemplateDataModelUtil.generateDataModel(
                testClassName,
                previousActionStringList,
                testOperationList,
                testButtonOperationCollectionList
            )
        } returns dateModel

        val outputDirPath =
            Paths.get(".${File.separator}src${File.separator}test${File.separator}kotlin${File.separator}output")

        every {
            Files.exists(outputDirPath)
        } returns false

        every {
            Files.createDirectory(outputDirPath)
        } returns null

        val template = mockk<Template>()
        every {
            TemplateFactory.create(classTemplateFileName)
        } returns template

        every {
            template.process(dateModel, any<Writer>())
        } returns Unit

        TestOperationFileWriter.writeFile(
            classTemplateFileName,
            testClassName,
            previousActionStringList,
            testOperationList,
            testButtonOperationCollectionList
        )

        verify(exactly = 1) {
            TemplateDataModelUtil.generateDataModel(
                testClassName,
                previousActionStringList,
                testOperationList,
                testButtonOperationCollectionList
            )

            Files.exists(outputDirPath)
            Files.createDirectory(outputDirPath)
            TemplateFactory.create(classTemplateFileName)
            template.process(dateModel, any<Writer>())
        }

    }
}