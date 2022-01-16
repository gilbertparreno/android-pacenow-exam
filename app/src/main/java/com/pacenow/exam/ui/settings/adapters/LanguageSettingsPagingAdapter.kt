package com.pacenow.exam.ui.settings.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.isGone
import androidx.core.view.updateLayoutParams
import coil.load
import com.pacenow.exam.R
import com.pacenow.exam.core.base.BasePagingDataAdapter
import com.pacenow.exam.core.enums.InsetContainerType.*
import com.pacenow.exam.core.extensions.*
import com.pacenow.exam.ui.settings.adapters.callbacks.LanguageSettingsDiffUtilItemCallback
import com.pacenow.exam.ui.settings.entities.CountryItem
import kotlinx.android.synthetic.main.view_settings_country_item.view.*

class LanguageSettingsPagingAdapter(
    private val onShowCancelMenu: (() -> Unit)? = null
) : BasePagingDataAdapter<CountryItem>(
    LanguageSettingsDiffUtilItemCallback()
) {

    private var editMode = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_settings_country_item))
    }

    override fun onBindViewHolder(view: View, item: CountryItem, position: Int) {
        with(view) {
            setInset(
                if (itemCount > 0) {
                    when (position) {
                        0 -> ROUNDED_TOP
                        itemCount - 1 -> ROUNDED_BOTTOM
                        else -> DEFAULT
                    }
                } else ROUNDED
            )
            updateLayoutParams<ViewGroup.MarginLayoutParams> {
                when (position) {
                    0 -> topMargin = dp(16)
                    itemCount - 1 -> bottomMargin = dp(16)
                    else -> {
                        topMargin = 0
                        bottomMargin = 0
                    }
                }
            }

            flag.load(item.flagUrl)
            country.text = item.country
            region.text = item.region

            checkBox.apply {
                isGone = !editMode
                isChecked = item.isSelected
            }

            setOnLongClickListener {
                editMode = true
                notifyDataSetChanged()
                onShowCancelMenu?.invoke()
                return@setOnLongClickListener true
            }
            setOnClickListener {
                if (editMode) {
                    setChecked(checkBox, position)
                }
            }
        }
    }

    private fun setChecked(checkBox: CheckBox, position: Int) {
        val selectedItemsCount = snapshot().items.filter { it.isSelected }.count()
        val selectedItem = snapshot().items[position]
        if (selectedItemsCount == 2 && !selectedItem.isSelected) {
            checkBox.showErrorSnackbar(checkBox.getString(R.string.selection_exceeded_error))
            return
        }
        checkBox.isChecked = !selectedItem.isSelected
        getItem(position)?.let {
            it.isSelected = !selectedItem.isSelected
        }
        notifyItemChanged(position)
    }

    fun deselectAllItems() {
        snapshot().items.filter {
            it.isSelected
        }.forEach {
            val index = snapshot().items.indexOf(it)
            getItem(index)?.let {
                it.isSelected = false
            }
        }
        editMode = false
        notifyDataSetChanged()
    }

    fun getSelectedItems(): String {
        return snapshot().items.filter { it.isSelected }
            .joinToString(", ") { it.country }
    }
}