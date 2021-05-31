package com.example.kotlinmvvmbase.util.adapter.hybrid.mock

import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding
import com.example.kotlinmvvmbase.util.adapter.hybrid.holder.HybridViewHolder
import com.example.kotlinmvvmbase.util.adapter.hybrid.mock.Duck

class DuckHolder(binding: ViewBinding) : HybridViewHolder<Duck, RecyclerViewItemBinding>(binding) {

    override fun bind(item: Duck) {
        binding.recyclerViewItemText.text = item.value.toString()
        when (item.value) {
           1 -> binding.root.setBackgroundColor(binding.root.context.resources.getColor(android.R.color.holo_blue_light))
           2 -> binding.root.setBackgroundColor(binding.root.context.resources.getColor(android.R.color.holo_green_light))
           4 -> binding.root.setBackgroundColor(binding.root.context.resources.getColor(android.R.color.holo_orange_light))
        }
    }
}