package jp.small_java_world.testopegen.element

import com.codeborne.selenide.*
import com.codeborne.selenide.files.FileFilter
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Coordinates
import java.awt.image.BufferedImage
import java.io.File
import java.time.Duration

class DummySelenideElement : SelenideElement {
    lateinit var textValue: String
    lateinit var valueValue: String

    override fun findElements(by: By?): MutableList<WebElement> {
        return mutableListOf()
    }

    override fun findElement(by: By?): WebElement {
        return DummySelenideElement()
    }

    override fun <X : Any?> getScreenshotAs(target: OutputType<X>): X {
        TODO("Not yet implemented getScreenshotAs")
    }

    override fun click(clickOption: ClickOptions) {
        TODO("Not yet implemented click clickOption")
    }

    override fun click() {
        TODO("Not yet implemented click")
    }

    override fun click(offsetX: Int, offsetY: Int) {
        TODO("Not yet implemented offsetX offsetY")
    }

    override fun submit() {
        TODO("Not yet implemented submit")
    }

    override fun sendKeys(vararg keysToSend: CharSequence?) {
        TODO("Not yet implemented sendKeys")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun getTagName(): String {
        return "dummyTagName"
    }

    override fun getAttribute(name: String): String? {
        return when(name) {
            "value" -> valueValue
            else -> ""
        }
    }

    override fun isSelected(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented isEnabled")
    }

    override fun getText(): String {
        return textValue
    }

    override fun isDisplayed(): Boolean {
        return true
    }

    override fun getLocation(): Point {
        TODO("Not yet implemented getLocation")
    }

    override fun getSize(): Dimension {
        TODO("Not yet implemented getSize")
    }

    override fun getRect(): Rectangle {
        TODO("Not yet implemented getRect")
    }

    override fun getCssValue(propertyName: String): String {
        TODO("Not yet implemented getCssValue")
    }

    override fun getWrappedDriver(): WebDriver {
        TODO("Not yet implemented getWrappedDriver")
    }

    override fun getWrappedElement(): WebElement {
        TODO("Not yet implemented getWrappedElement")
    }

    override fun getCoordinates(): Coordinates {
        TODO("Not yet implemented getCoordinates")
    }

    override fun getId(): String {
        TODO("Not yet implemented getId")
    }

    override fun setValue(text: String?): SelenideElement {
        TODO("Not yet implemented setValue")
    }

    override fun `val`(text: String?): SelenideElement {
        TODO("Not yet implemented val text")
    }

    override fun `val`(): String? {
        TODO("Not yet implemented val")
    }

    override fun append(text: String): SelenideElement {
        TODO("Not yet implemented append")
    }

    override fun pressEnter(): SelenideElement {
        TODO("Not yet implemented pressEnter")
    }

    override fun pressTab(): SelenideElement {
        TODO("Not yet implemented pressTab")
    }

    override fun pressEscape(): SelenideElement {
        TODO("Not yet implemented pressEscape")
    }

    override fun getAlias(): String? {
        TODO("Not yet implemented getAlias")
    }

    override fun text(): String {
        TODO("Not yet implemented text")
    }

    override fun getOwnText(): String {
        TODO("Not yet implemented getOwnText")
    }

    override fun innerText(): String {
        TODO("Not yet implemented innerText")
    }

    override fun innerHtml(): String {
        TODO("Not yet implemented innerHtml")
    }

    override fun attr(attributeName: String): String? {
        TODO("Not yet implemented attr")
    }

    override fun name(): String? {
        TODO("Not yet implemented name")
    }

    override fun getValue(): String? {
        return valueValue
    }

    override fun pseudo(pseudoElementName: String, propertyName: String): String {
        TODO("Not yet implemented pseudo1")
    }

    override fun pseudo(pseudoElementName: String): String {
        TODO("Not yet implemented pseudo2")
    }

    override fun selectRadio(value: String): SelenideElement {
        TODO("Not yet implemented selectRadio")
    }

    override fun data(dataAttributeName: String): String? {
        TODO("Not yet implemented data")
    }

    override fun exists(): Boolean {
        TODO("Not yet implemented exists")
    }

    override fun `is`(condition: Condition): Boolean {
        TODO("Not yet implemented is")
    }

    override fun has(condition: Condition): Boolean {
        TODO("Not yet implemented has")
    }

    override fun setSelected(selected: Boolean): SelenideElement {
        TODO("Not yet implemented setSelected")
    }

    override fun should(vararg condition: Condition?): SelenideElement {
        TODO("Not yet implemented should")
    }

    override fun should(condition: Condition, timeout: Duration): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun shouldHave(vararg condition: Condition?): SelenideElement {
        TODO("Not yet implemented shouldHave1")
    }

    override fun shouldHave(condition: Condition, timeout: Duration): SelenideElement {
        TODO("Not yet implemented shouldHave2")
    }

    override fun shouldBe(vararg condition: Condition?): SelenideElement {
        TODO("Not yet implemented shouldBe1")
    }

    override fun shouldBe(condition: Condition, timeout: Duration): SelenideElement {
        TODO("Not yet implemented shouldBe2")
    }

    override fun shouldNot(vararg condition: Condition?): SelenideElement {
        TODO("Not yet implemented shouldNot1")
    }

    override fun shouldNot(condition: Condition, timeout: Duration): SelenideElement {
        TODO("Not yet implemented shouldNot2")
    }

    override fun shouldNotHave(vararg condition: Condition?): SelenideElement {
        TODO("Not yet implemented shouldNotHave1")
    }

    override fun shouldNotHave(condition: Condition, timeout: Duration): SelenideElement {
        TODO("Not yet implemented shouldNotHave2")
    }

    override fun shouldNotBe(vararg condition: Condition?): SelenideElement {
        TODO("Not yet implemented shouldNotBe1")
    }

    override fun shouldNotBe(condition: Condition, timeout: Duration): SelenideElement {
        TODO("Not yet implemented shouldNotBe2")
    }

    override fun waitUntil(condition: Condition, timeoutMilliseconds: Long): SelenideElement {
        TODO("Not yet implemented waitUntil2")
    }

    override fun waitUntil(
        condition: Condition,
        timeoutMilliseconds: Long,
        pollingIntervalMilliseconds: Long
    ): SelenideElement {
        TODO("Not yet implemented waitUntil2")
    }

    override fun waitWhile(condition: Condition, timeoutMilliseconds: Long): SelenideElement {
        TODO("Not yet implemented waitUntil3")
    }

    override fun waitWhile(
        condition: Condition,
        timeoutMilliseconds: Long,
        pollingIntervalMilliseconds: Long
    ): SelenideElement {
        TODO("Not yet implemented waitUntil4")
    }

    override fun `as`(alias: String): SelenideElement {
        TODO("Not yet implemented as")
    }

    override fun parent(): SelenideElement {
        TODO("Not yet implemented parent")
    }

    override fun sibling(index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun preceding(index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun lastChild(): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun closest(tagOrClass: String): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun find(cssSelector: String): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun find(cssSelector: String, index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun find(selector: By): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun find(selector: By, index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun `$`(cssSelector: String): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun `$`(cssSelector: String, index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun `$`(selector: By): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun `$`(selector: By, index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun `$x`(xpath: String): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun `$x`(xpath: String, index: Int): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun findAll(cssSelector: String): ElementsCollection {
        TODO("Not yet implemented")
    }

    override fun findAll(selector: By): ElementsCollection {
        TODO("Not yet implemented")
    }

    override fun `$$`(cssSelector: String): ElementsCollection {
        TODO("Not yet implemented")
    }

    override fun `$$`(selector: By): ElementsCollection {
        TODO("Not yet implemented")
    }

    override fun `$$x`(xpath: String): ElementsCollection {
        TODO("Not yet implemented")
    }

    override fun uploadFromClasspath(vararg fileName: String?): File {
        TODO("Not yet implemented")
    }

    override fun uploadFile(vararg file: File?): File {
        TODO("Not yet implemented")
    }

    override fun selectOption(vararg index: Int) {
        TODO("Not yet implemented")
    }

    override fun selectOption(vararg text: String?) {
        TODO("Not yet implemented")
    }

    override fun selectOptionContainingText(text: String) {
        TODO("Not yet implemented")
    }

    override fun selectOptionByValue(vararg value: String?) {
        TODO("Not yet implemented")
    }

    override fun getSelectedOption(): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun getSelectedOptions(): ElementsCollection {
        TODO("Not yet implemented")
    }

    override fun getSelectedValue(): String? {
        TODO("Not yet implemented")
    }

    override fun getSelectedText(): String {
        TODO("Not yet implemented")
    }

    override fun scrollTo(): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun scrollIntoView(alignToTop: Boolean): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun scrollIntoView(scrollIntoViewOptions: String): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun download(): File {
        TODO("Not yet implemented")
    }

    override fun download(timeout: Long): File {
        TODO("Not yet implemented")
    }

    override fun download(fileFilter: FileFilter): File {
        TODO("Not yet implemented")
    }

    override fun download(timeout: Long, fileFilter: FileFilter): File {
        TODO("Not yet implemented")
    }

    override fun download(options: DownloadOptions): File {
        TODO("Not yet implemented")
    }

    override fun getSearchCriteria(): String {
        TODO("Not yet implemented")
    }

    override fun toWebElement(): WebElement {
        TODO("Not yet implemented")
    }

    override fun contextClick(): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun doubleClick(): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun hover(): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun dragAndDropTo(targetCssSelector: String): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun dragAndDropTo(target: WebElement): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun dragAndDropTo(targetCssSelector: String, options: DragAndDropOptions): SelenideElement {
        TODO("Not yet implemented")
    }

    override fun <ReturnType : Any?> execute(command: Command<ReturnType>): ReturnType {
        TODO("Not yet implemented")
    }

    override fun isImage(): Boolean {
        TODO("Not yet implemented")
    }

    override fun screenshot(): File? {
        TODO("Not yet implemented")
    }

    override fun screenshotAsImage(): BufferedImage? {
        TODO("Not yet implemented")
    }
}