package com.pacenow.exam.core.extensions

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.SystemClock
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pacenow.exam.R
import com.pacenow.exam.core.enums.InsetContainerType
import com.pacenow.exam.core.helpers.InsetHelper
import com.pacenow.exam.core.utils.ScreenHelper

fun View.dp(value: Int): Int = context.dp(value)
fun View.dp(value: Float): Float = context.dp(value)
fun View.sp(value: Float): Float = context.sp(value)

fun View.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return context.getCompatDrawable(id)
}

fun View.getString(@StringRes id: Int): String {
    return context.getString(id)
}

fun View.showErrorSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    val snackbar = Snackbar.make(this, message, duration)

    snackbar.setTextColor(ContextCompat.getColor(context, R.color.white))
    snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    snackbar.setBackgroundTint(ContextCompat.getColor(context, R.color.error))
    snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines =
        5

    snackbar.setAction(R.string.dismiss) {
        snackbar.dismiss()
    }

    val view = snackbar.view
    val params = view.layoutParams as? CoordinatorLayout.LayoutParams
    params?.let {
        it.gravity = Gravity.TOP
        it.setMargins(
            it.leftMargin,
            it.topMargin + ScreenHelper.getStatusBarHeightFromAndroid(),
            it.rightMargin,
            it.bottomMargin
        )
        view.layoutParams = it
    }

    snackbar.show()
}

inline fun View.setDebounceClickListener(
    debounceTime: Long = 600L,
    crossinline action: () -> Unit
) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action.invoke()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.getDrawable(@DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(context, resId)
}

fun View.getColor(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(context, resId)
}

fun View.setInset(
    insetContainerType: InsetContainerType,
    applyForeground: Boolean = true,
    @ColorInt backgroundColor: Int? = null,
    @DrawableRes insetBackgroundRes: Int? = null,
    @DrawableRes insetForegroundRes: Int? = null
) {
    val backgroundRes = insetBackgroundRes ?: InsetHelper.getInsetBackground(insetContainerType)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val foregroundRes = insetForegroundRes ?: InsetHelper.getInsetForeground(insetContainerType)
        foreground = if (applyForeground)
            getDrawable(foregroundRes)
        else null
    }

    background = getDrawable(backgroundRes)
    background = (background as GradientDrawable).also {
        it.setColor(backgroundColor ?: getColor(R.color.white))
    }
}