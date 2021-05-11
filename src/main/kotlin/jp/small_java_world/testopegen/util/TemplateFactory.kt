package jp.small_java_world.testopegen.util

import freemarker.template.Configuration
import freemarker.template.Template
import jp.small_java_world.testopegen.generator.SelectOperationGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class TemplateFactory {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(SelectOperationGenerator::class.java)

        @JvmStatic
        fun create(classTemplateFileName: String): Template {
            val configuration = Configuration(Configuration.VERSION_2_3_23)
            configuration.setDirectoryForTemplateLoading(File("./src/main/resources/template"))
            return configuration.getTemplate(classTemplateFileName)
        }
    }
}