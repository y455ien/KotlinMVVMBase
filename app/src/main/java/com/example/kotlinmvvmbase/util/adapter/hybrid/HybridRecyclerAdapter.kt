package com.example.kotlinmvvmbase.util.adapter.hybrid

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.util.adapter.hybrid.holder.HybridViewHolder
import com.example.kotlinmvvmbase.util.adapter.hybrid.item.HybridAdapterItem

class HybridRecyclerAdapter(private val items: List<HybridAdapterItem>,
                            private val typeFactory: HybridTypeFactory)
    : RecyclerView.Adapter<HybridViewHolder<HybridAdapterItem, ViewBinding>>() {

    override fun getItemViewType(position: Int): Int {
        return items[position].type(typeFactory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HybridViewHolder<HybridAdapterItem, ViewBinding> {
        val binding = typeFactory.binding(viewType, parent)
        return typeFactory.holder(viewType, binding) as HybridViewHolder<HybridAdapterItem, ViewBinding>
    }

    override fun onBindViewHolder(holder: HybridViewHolder<HybridAdapterItem, ViewBinding>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}