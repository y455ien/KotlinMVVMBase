package com.example.kotlinmvvmbase.util.adapter.hybrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding

class ListViewTypeFactory {

    fun type(value: Any) : Int {
        return when (value) {
            is Duck -> R.layout.recycler_view_item
            else -> -1
        }
    }

    fun binding(type: Int, parent: ViewGroup) : ViewBinding {
        return when(type) {
            R.layout.recycler_view_item -> RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            2 -> MouseHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }

    fun holder(type: Int, view: ViewBinding): BetterViewHolder<*, *> {
        return when(type) {
            R.layout.recycler_view_item -> DuckHolder(view)
//            2 -> MouseHolder(view)
            else -> throw RuntimeException("Illegal view type")
        }
    }
}