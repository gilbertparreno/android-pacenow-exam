package com.pacenow.exam.core.helpers

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.pacenow.exam.R

object ToastHelper {

    fun showToast(context: Context, @StringRes resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }

    fun showSuccessToast(context: Context, @StringRes resId: Int) {
        createSuccessToast(context).apply {
            setText(resId)
        }.show()
    }

    fun showSuccessToast(context: Context, text: String) {
        createSuccessToast(context).apply {
            setText(text)
        }.show()
    }

    fun showErrorToast(context: Context, @StringRes resId: Int) {
        createErrorToast(context).apply {
            setText(resId)
        }.show()
    }

    fun showErrorToast(context: Context, text: String) {
        createErrorToast(context).apply {
            setText(text)
        }.show()
    }

    private fun createErrorToast(context: Context) =
        createColorToast(
            context = context,
            backgroundColorResId = R.color.rose_red,
            textColorResId = R.color.white,
            toastLength = Toast.LENGTH_LONG
        )

    private fun createSuccessToast(context: Context) =
        createColorToast(
            context = context,
            backgroundColorResId = R.color.lime_green,
            textColorResId = R.color.white,
            toastLength = Toast.LENGTH_SHORT
        )

    private fun createColorToast(
        context: Context,
        @ColorRes backgroundColorResId: Int,
        @ColorRes textColorResId: Int,
        toastLength: Int
    ): Toast {
        return Toast.makeText(context, "", toastLength).apply {
            view?.background?.setColorFilter(
                ContextCompat.getColor(context, backgroundColorResId),
                PorterDuff.Mode.SRC_IN
            )
            view?.findViewById<TextView>(android.R.id.message)
                ?.setTextColor(getColor(context, textColorResId))
        }
    }

    private fun getColor(context: Context, @ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }
}