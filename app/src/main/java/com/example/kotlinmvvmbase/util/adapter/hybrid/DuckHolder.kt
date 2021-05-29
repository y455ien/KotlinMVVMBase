package com.example.kotlinmvvmbase.util.adapter.hybrid

import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding

class DuckHolder(binding: ViewBinding) : BetterViewHolder<DuckListItemViewModel, RecyclerViewItemBinding>(binding) {

    override fun bind(item: DuckListItemViewModel) {
        binding.recyclerViewItemText.text = item.duck.value.toString()
        when (item.duck.value) {
           1 -> binding.root.setBackgroundColor(binding.root.context.resources.getColor(android.R.color.holo_blue_light))
           2 -> binding.root.setBackgroundColor(binding.root.context.resources.getColor(android.R.color.holo_green_light))
           4 -> binding.root.setBackgroundColor(binding.root.context.resources.getColor(android.R.color.holo_orange_light))
        }
    }
}