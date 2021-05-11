package jp.small_java_world.testopegen

import io.mockk.unmockkAll
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.charset.StandardCharsets

open class TestBase {
    private val logger: Logger = LoggerFactory.getLogger(TestBase::class.java)

    open fun afterEach() = unmockkAll()

    /**
     * 指定ファイルの中身を期待値としてactualと一致するか検証します。
     * ファイルのパスはcom.example.todoList.controller.TopControllerからfileName="expectedListResult.txt"
     * で呼び出された場合はbuildディレクトリの配下のclasses/kotlin/test/com/example/todoList/controller/expectedListResult.txt
     * となります。
     */
    fun assertFileEquals(expectedResultFileName: String, actual: String) {
        logger.info("assertFileEquals expectedResultFileName={} start", expectedResultFileName)
        val expectedResult = readText(expectedResultFileName)
        assertEquals(expectedResult, actual)
    }

    fun assertFileEquals(expectedResultFileName: String, actual: List<String>) {
        logger.info("assertFileEquals expectedResultFileName={} start", expectedResultFileName)
        val expectedResult = readText(expectedResultFileName)
        val actualBuilder = StringBuilder()
        actual.forEach { actualBuilder.appendLine(it) }
        assertEquals(expectedResult, actualBuilder.toString())
    }

    /**
     * com.example.todoList.controller.TopControllerからfileName="expectedListResult.txt"
     * で呼び出された場合はbuildディレクトリの配下のclasses/kotlin/test/com/example/todoList/controller/expectedListResult.txt
     * を読み込んで内容の文字列を返却します。
     */
    private fun readText(fileName: String): String {
        val url = this.javaClass.getResource(".")
        val fileFullPath = url.path + File.separator + fileName
        val targetFile = File(fileFullPath)
        if (targetFile.exists()) {
            return FileUtils.readFileToString(targetFile, StandardCharsets.UTF_8);
        }
        return "not exist fileName:${targetFile.absolutePath}"
    }
}