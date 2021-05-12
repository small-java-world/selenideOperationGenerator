package output

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import jp.small_java_world.testopegen.util.*

class InputTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            Configuration.browser = WebDriverRunner.CHROME;
        }
    }

    @BeforeEach
    fun beforeEach() {
        Selenide.open("file://C:/workspace/SelenideOperationGenerator/html/input.html")
        SelenideUtil.shouldHaveAttributeByCssSelector("#notDisplayButton", "style", "display: none;")

    }

    @Test
    fun `button0 test operation`() {
        /**************** cssSelector #addButton の処理 start ****************/
        //ボタンの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#addButton")
        
        SelenideUtil.clickByCssSelector("#addButton")
        

    }

    @Test
    fun `button1 test operation`() {
        /**************** cssSelector #deleteButton の処理 start ****************/
        //ボタンの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#deleteButton")
        
        SelenideUtil.clickByCssSelector("#deleteButton")
        

    }

    @Test
    fun `button2 test operation`() {
        /**************** cssSelector #delayDeleteButton の処理 start ****************/
        //ボタンの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#delayDeleteButton")
        
        SelenideUtil.clickByCssSelector("#delayDeleteButton")
        

    }

    @Test
    fun `button3 test operation`() {
        /**************** cssSelector #notDisplayButton の処理 start ****************/
        //ボタンの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#notDisplayButton")
        
        // clickByCssSelector fail
        //SelenideUtil.clickByCssSelector("#notDisplayButton")
        
        SelenideUtil.clickByCssSelectorUseJS("#notDisplayButton")
        

    }

    @Test
    fun `input test operation`() {
        /**************** cssSelector #grade1 の処理 start ****************/
        //ラジオボタンの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#grade1")
        
        //ラジオボタンの選択
        SelenideUtil.selectRadioByCssSelector("input[name=grade]", "1")
        
        //ラジオボタンの選択の検証
        SelenideUtil.shouldBeSelectedByCssSelector("#grade1")
        
        /**************** cssSelector #grade2 の処理 start ****************/
        //ラジオボタンの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#grade2")
        
        //ラジオボタンの選択
        SelenideUtil.selectRadioByCssSelector("input[name=grade]", "2")
        
        //ラジオボタンの選択の検証
        SelenideUtil.shouldBeSelectedByCssSelector("#grade2")
        
        /**************** cssSelector #lang1 の処理 start ****************/
        //チェックボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#lang1")
        
        //チェックボックスのチェック
        SelenideUtil.checkByCssSelector("#lang1")
        
        //チェックされたことの検証
        SelenideUtil.shouldBeSelectedByCssSelector("#lang1")
        
        //チェックボックスのアンチェック
        SelenideUtil.unCheckByCssSelector("#lang1")
        
        //チェックさていないことの検証
        SelenideUtil.shouldBeNotSelectedByCssSelector("#lang1")
        
        /**************** cssSelector #lang2 の処理 start ****************/
        //チェックボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#lang2")
        
        //チェックボックスのチェック
        SelenideUtil.checkByCssSelector("#lang2")
        
        //チェックされたことの検証
        SelenideUtil.shouldBeSelectedByCssSelector("#lang2")
        
        //チェックボックスのアンチェック
        SelenideUtil.unCheckByCssSelector("#lang2")
        
        //チェックさていないことの検証
        SelenideUtil.shouldBeNotSelectedByCssSelector("#lang2")
        
        /**************** cssSelector #p-input1-2-1 > #input1 の処理 start ****************/
        //テキストボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#p-input1-2-1 > #input1")
        
        //テキストボックスへの入力
        SelenideUtil.inputTextByCssSelector("#p-input1-2-1 > #input1", "fOAR")
        
        //テキストボックスへ入力した値の検証
        SelenideUtil.shouldBeValueByCssSelector("#p-input1-2-1 > #input1", "fOAR")
        
        /**************** cssSelector #p-input1-2-2 > #input1 の処理 start ****************/
        //テキストボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("#p-input1-2-2 > #input1")
        
        //テキストボックスへの入力
        SelenideUtil.inputTextByCssSelector("#p-input1-2-2 > #input1", "CRRA")
        
        //テキストボックスへ入力した値の検証
        SelenideUtil.shouldBeValueByCssSelector("#p-input1-2-2 > #input1", "CRRA")
        
        /**************** cssSelector input[name='input1-1'] の処理 start ****************/
        //テキストボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("input[name='input1-1']")
        
        //テキストボックスへの入力
        SelenideUtil.inputTextByCssSelector("input[name='input1-1']", "t3Ww")
        
        //テキストボックスへ入力した値の検証
        SelenideUtil.shouldBeValueByCssSelector("input[name='input1-1']", "t3Ww")
        
        /**************** cssSelector input[hoge='1'] の処理 start ****************/
        //テキストボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("input[hoge='1']")
        
        //テキストボックスへの入力
        SelenideUtil.inputTextByCssSelector("input[hoge='1']", "rkgl")
        
        //テキストボックスへ入力した値の検証
        SelenideUtil.shouldBeValueByCssSelector("input[hoge='1']", "rkgl")
        
        /**************** cssSelector input[hoge='2'] の処理 start ****************/
        //テキストボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("input[hoge='2']")
        
        //テキストボックスへの入力
        SelenideUtil.inputTextByCssSelector("input[hoge='2']", "xmLP")
        
        //テキストボックスへ入力した値の検証
        SelenideUtil.shouldBeValueByCssSelector("input[hoge='2']", "xmLP")
        
        /**************** cssSelector html > body > input[hoge='3'] の処理 start ****************/
        //テキストボックスの存在確認
        SelenideUtil.confirmExistenceByCssSelector("html > body > input[hoge='3']")
        
        //テキストボックスへの入力
        SelenideUtil.inputTextByCssSelector("html > body > input[hoge='3']", "Cjd1")
        
        //テキストボックスへ入力した値の検証
        SelenideUtil.shouldBeValueByCssSelector("html > body > input[hoge='3']", "Cjd1")
        

    }


}