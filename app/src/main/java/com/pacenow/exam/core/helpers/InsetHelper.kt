package com.pacenow.exam.core.helpers

import androidx.annotation.DrawableRes
import com.pacenow.exam.R
import com.pacenow.exam.core.enums.InsetContainerType
import com.pacenow.exam.core.enums.InsetContainerType.*

object InsetHelper {

    @DrawableRes
    fun getInsetBackground(insetContainerType: InsetContainerType): Int {
        return when (insetContainerType) {
            ROUNDED -> R.drawable.background_inset_rounded_corners
            ROUNDED_TOP -> R.drawable.background_inset_top_rounded_corners
            ROUNDED_BOTTOM -> R.drawable.background_inset_bottom_rounded_corners
            DEFAULT -> R.drawable.background_inset_default
        }
    }

    @DrawableRes
    fun getInsetForeground(insetContainerType: InsetContainerType): Int {
        return when (insetContainerType) {
            ROUNDED -> R.drawable.ripple_default_rounded_11dp
            ROUNDED_TOP -> R.drawable.ripple_top_rounded_11dp
            ROUNDED_BOTTOM -> R.drawable.ripple_bottom_rounded_11dp
            DEFAULT -> R.drawable.ripple_default
        }
    }
}