package com.pacenow.exam.ui.settings.adapters.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.pacenow.exam.ui.settings.entities.CountryItem

class LanguageSettingsDiffUtilItemCallback : DiffUtil.ItemCallback<CountryItem>() {
    override fun areItemsTheSame(
        oldItem: CountryItem,
        newItem: CountryItem
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: CountryItem,
        newItem: CountryItem
    ) = oldItem.code == newItem.code
}