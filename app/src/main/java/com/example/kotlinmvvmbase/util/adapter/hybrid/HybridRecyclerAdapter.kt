package com.example.kotlinmvvmbase.util.adapter.hybrid

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class HybridRecyclerAdapter(private val items: List<ListViewModel>,
                            private val typeFactory: ListViewTypeFactory)
    : RecyclerView.Adapter<BetterViewHolder<ListViewModel, ViewBinding>>() {

    override fun getItemViewType(position: Int): Int {
        return items[position].type(typeFactory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetterViewHolder<ListViewModel, ViewBinding> {
        val binding = typeFactory.binding(viewType, parent)
        return typeFactory.holder(viewType, binding) as BetterViewHolder<ListViewModel, ViewBinding>
    }

    override fun onBindViewHolder(holder: BetterViewHolder<ListViewModel, ViewBinding>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}