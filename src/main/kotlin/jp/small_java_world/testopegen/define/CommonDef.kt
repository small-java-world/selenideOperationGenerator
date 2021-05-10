package jp.small_java_world.testopegen.define

class CommonDef{
    companion object {
        const val TARGET_CSS_SELECTOR = "%targetCssSelector"
        const val INPUT_VALUE = "%inputTextValue"
        const val INPUT_VALUE_TEMPLATE = "inputTextByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val CLICK_TEMPLATE = "clickByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val CLICK_USE_JS_TEMPLATE = "clickByCssSelectorUseJS(\"$TARGET_CSS_SELECTOR\")"
        const val SELECT_RADIO_TEMPLATE = "selectRadioByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val CHECK_TEMPLATE = "checkByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val UNCHECK_TEMPLATE = "unCheckByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val SELECT_OPTION_BY_VALUE_TEMPLATE = "selectOptionByValueByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val SELECT_SELECT_OPTION_TEMPLATE = "selectOptionByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"

        const val CONFIRM_EXISTENCE_TEMPLATE = "confirmExistenceByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val CONFIRM_VALUE_TEMPLATE = "shouldBeValueByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val CONFIRM_SELECTED_TEMPLATE = "shouldBeSelectedByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val CONFIRM_NOT_SELECTED_TEMPLATE = "shouldBeNotSelectedByCssSelector(\"$TARGET_CSS_SELECTOR\")"


    }
}

