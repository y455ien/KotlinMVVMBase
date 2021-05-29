package com.example.kotlinmvvmbase.util.adapter.hybrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding
import com.example.kotlinmvvmbase.util.adapter.hybrid.holder.HybridViewHolder
import com.example.kotlinmvvmbase.util.adapter.hybrid.item.Human

class HybridTypeFactory {

    fun type(value: Any) : Int {
        return when (value) {
            is Duck -> R.layout.recycler_view_item
            is Human -> android.R.layout.simple_list_item_2
            else -> -1
        }
    }

    fun binding(type: Int, parent: ViewGroup) : ViewBinding {
        return when(type) {
            R.layout.recycler_view_item -> RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            android.R.layout.simple_list_item_2 -> RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            else -> throw RuntimeException("Illegal view type")
        }
    }

    fun holder(type: Int, viewBinding: ViewBinding): HybridViewHolder<*, *> {
        return when(type) {
            R.layout.recycler_view_item -> DuckHolder(viewBinding)
            android.R.layout.simple_list_item_2 -> HumanHolder(viewBinding)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}