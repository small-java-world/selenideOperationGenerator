import com.codeborne.selenide.Selenide
import jp.small_java_world.testopegen.TestExampleOperationGenerator
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val testOperationGenerator = TestExampleOperationGenerator()

    testOperationGenerator.generate(
        "testOperationClassTemplate.ftl",
        "InputTest",
        listOf("Selenide.open(\"file:///C:/example/input.html\")")
    )
    { Selenide.open("file:///C:/example/input.html") }

}
