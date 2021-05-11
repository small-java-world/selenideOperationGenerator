package output

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import jp.small_java_world.testopegen.util.*

class ${testClassName} {
    companion object {
        @JvmStatic
        @BeforeAll
        fun initialize() {
            Configuration.browser = WebDriverRunner.CHROME;
        }
    }

    @BeforeEach
    fun beforeEach() {
        ${previousAction}
    }

    <#list testMethodDataList as test>
    @Test
    fun `${test.name}`() {
        ${test.operation}
    }

    </#list>

}