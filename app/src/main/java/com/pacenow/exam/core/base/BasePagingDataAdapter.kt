package com.pacenow.exam.core.base

import android.view.View
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingDataAdapter<T : Any>(
    diffUtil: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, BasePagingDataAdapter<T>.ViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            onBindViewHolder(holder.itemView, it, position)
        }
    }

    abstract fun onBindViewHolder(view: View, item: T, position: Int)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}