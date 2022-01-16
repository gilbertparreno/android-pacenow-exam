package com.pacenow.exam.core.enums

enum class InsetContainerType(val value: Int) {
    DEFAULT(0),
    ROUNDED(1),
    ROUNDED_TOP(2),
    ROUNDED_BOTTOM(3);

    companion object {
        fun getInsetType(value: Int): InsetContainerType {
            return values().firstOrNull {
                it.value == value
            } ?: DEFAULT
        }
    }
}