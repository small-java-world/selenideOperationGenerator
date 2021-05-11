package jp.small_java_world.testopegen.define

const val TAG_NAME_INPUT = "input"
const val TAG_NAME_SELECT = "select"

enum class TargetElementType(val tagName: String, val type: String, val tagNameJp: String) {
    INPUT_TEXT(TAG_NAME_INPUT, "text", "テキストボックス"),
    INPUT_BUTTON(TAG_NAME_INPUT, "button", "ボタン"),
    INPUT_RADIO(TAG_NAME_INPUT, "radio", "ラジオボタン"),
    INPUT_CHECKBOX(TAG_NAME_INPUT, "checkbox", "チェックボックス"),
    SELECT(TAG_NAME_SELECT, "select", "セレクトボックス")
}