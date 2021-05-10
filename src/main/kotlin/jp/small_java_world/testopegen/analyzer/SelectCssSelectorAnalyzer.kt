package jp.small_java_world.testopegen.analyzer

import jp.small_java_world.testopegen.define.TAG_NAME_INPUT
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.isDuplicateByCssSelector
import org.jsoup.nodes.Attribute
import org.jsoup.nodes.Element
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SelectCssSelectorAnalyzer : CssSelectorAnalyzer {
    override fun getRemoveAttrNameList(): List<String> {
        return listOf()
    }
}