package jp.small_java_world.testopegen.analyzer

import jp.small_java_world.testopegen.define.TAG_NAME_INPUT
import jp.small_java_world.testopegen.define.TAG_NAME_SELECT
import jp.small_java_world.testopegen.define.TargetElementType
import jp.small_java_world.testopegen.util.SelenideUtil
import org.jsoup.nodes.Attribute
import org.jsoup.nodes.Element

class CssSelectorAnalyzer {
    /**
     * targetElementを一意に識別可能なCssSelectorの文字列がfirst
     * targetElementに対応するTargetElementTypeがsecondの
     * Pair<String?, TargetElementType?>を返却します。
     *
     * targetElement.cssSelector()で要素が重複していない場合は、targetElement.cssSelector()の値を返却します。
     *
     * tagName[attrName='attrValue']のcssSelectorで一意になるか確認する。
     * タグを限定すればid or nameで一意な可能性は高いのでこの属性を優先処理
     * inputであれば、input[id='idValue']とinput[name='nameValue']など
     * targetElement単独の評価で一意にならない場合は、
     * 親要素のCssSelector > input[id='idValue']などでの評価を行う。
     *
     * @param targetElement org.jsoup.nodes.Node
     * @return Pair<String?, TargetElementType?>
     */
    fun getCssSelectorElementTypePair(targetElement: Element?): Pair<String?, TargetElementType?> {
        checkNotNull(targetElement) { "targetElement cannot be null" }

        val tagName = targetElement.tagName()
        val inputType = targetElement.attr("type")
        val elementType = getElementType(tagName, inputType)

        var cssSelectorValue = targetElement.cssSelector()
        //targetElement.cssSelector()で要素が重複していない場合は、targetElement.cssSelector()の値を返却
        if (!SelenideUtil.isDuplicateByCssSelector(cssSelectorValue!!)) {
            return cssSelectorValue to elementType
        }

        // 親要素のCssSelectorを取得
        val parentCssSelectorValue = getParentCssSelector(targetElement)

        // 評価する属性のリストの作成のために全属性のリストを取得
        val attributes = targetElement.attributes()
        val idAttribute = Attribute("id", attributes.get("id"))
        val nameAttribute = Attribute("name", attributes.get("name"))

        //idとnameとタグごとの除外属性を除去
        (getRemoveAttrNameList(elementType) + listOf("id", "name")).forEach { attributes.remove(it) }

        //idとname属性を優先するattributeListを生成
        val attributeList = listOf(idAttribute, nameAttribute) + attributes

        // まずは、対象のtagName[attrName='attrValue']で判定
        // うまくいかない場合、親のCssSelector > 対象のtagName[attrName='attrValue']で判定
        for (parentCssSelector in listOf(null, parentCssSelectorValue)) {
            for (attribute in attributeList) {
                //targetElement単独でのtagName[attrName='attrValue']のcssSelectorの文字列生成 idの場合は#id
                val targetCssSelector =
                    attribute.run { if (key.equals("id")) "#${value}" else "$tagName[${key}='${value}']" }

                //parentCssSelectorを加味したのcssSelectorの文字列生成
                cssSelectorValue =
                    parentCssSelector?.let {
                        "$it > $targetCssSelector"
                    } ?: targetCssSelector

                //cssSelectorValueでHTML要素が一意であれば結果をリターン
                if (!SelenideUtil.isDuplicateByCssSelector(cssSelectorValue)) {
                    return cssSelectorValue to elementType
                }
            }
        }

        return null to null
    }

    private fun getRemoveAttrNameList(targetElementType: TargetElementType?): List<String> {
        return when (targetElementType) {
            TargetElementType.INPUT_TEXT -> listOf("type", "value", "size", "maxlength")
            else -> listOf("type")
        }
    }

    private fun getParentCssSelector(targetElement: Element?): String? {
        val parent = targetElement?.parent() ?: return null
        var cssSelectorValue = parent.cssSelector()
        if (!SelenideUtil.isDuplicateByCssSelector(cssSelectorValue)) {
            return cssSelectorValue
        }

        cssSelectorValue = "${getParentCssSelector(parent)} > $parent.tagName()"
        if (!SelenideUtil.isDuplicateByCssSelector(cssSelectorValue)) {
            return cssSelectorValue
        }

        return null
    }

    private fun getElementType(tagName: String?, inputType: String?): TargetElementType? {
        return when (tagName) {
            TAG_NAME_INPUT -> {
                when (inputType) {
                    TargetElementType.INPUT_BUTTON.type -> TargetElementType.INPUT_BUTTON
                    TargetElementType.INPUT_TEXT.type -> TargetElementType.INPUT_TEXT
                    TargetElementType.INPUT_RADIO.type -> TargetElementType.INPUT_RADIO
                    TargetElementType.INPUT_CHECKBOX.type -> TargetElementType.INPUT_CHECKBOX
                    else -> null
                }
            }
            TAG_NAME_SELECT -> TargetElementType.SELECT
            else -> null
        }
    }
}