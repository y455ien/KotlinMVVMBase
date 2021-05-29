package com.example.kotlinmvvmbase.util.adapter.hybrid

import androidx.viewbinding.ViewBinding
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding
import com.example.kotlinmvvmbase.util.adapter.hybrid.holder.HybridViewHolder
import com.example.kotlinmvvmbase.util.adapter.hybrid.item.Human

class HumanHolder(binding: ViewBinding) :  HybridViewHolder<Human, RecyclerViewItemBinding>(binding){
    override fun bind(item: Human) {
        binding.recyclerViewItemText.text = item.name
    }
}