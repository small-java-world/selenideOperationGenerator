package jp.small_java_world.testopegen.define

class CommonDef{
    companion object {
        const val TARGET_CSS_SELECTOR = "%targetCssSelector"
        const val INPUT_VALUE = "%inputTextValue"
        const val INPUT_VALUE_TEMPLATE = "SelenideUtil.inputTextByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val CLICK_TEMPLATE = "SelenideUtil.clickByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val CLICK_USE_JS_TEMPLATE = "SelenideUtil.clickByCssSelectorUseJS(\"$TARGET_CSS_SELECTOR\")"
        const val SELECT_RADIO_TEMPLATE = "SelenideUtil.selectRadioByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val CHECK_TEMPLATE = "SelenideUtil.checkByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val UNCHECK_TEMPLATE = "SelenideUtil.unCheckByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val SELECT_OPTION_BY_VALUE_TEMPLATE = "SelenideUtil.selectOptionByValueByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val SELECT_SELECT_OPTION_TEMPLATE = "SelenideUtil.selectOptionByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"

        const val CONFIRM_EXISTENCE_TEMPLATE = "SelenideUtil.confirmExistenceByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val CONFIRM_VALUE_TEMPLATE = "SelenideUtil.shouldBeValueByCssSelector(\"$TARGET_CSS_SELECTOR\", \"$INPUT_VALUE\")"
        const val CONFIRM_SELECTED_TEMPLATE = "SelenideUtil.shouldBeSelectedByCssSelector(\"$TARGET_CSS_SELECTOR\")"
        const val CONFIRM_NOT_SELECTED_TEMPLATE = "SelenideUtil.shouldBeNotSelectedByCssSelector(\"$TARGET_CSS_SELECTOR\")"

    }
}

