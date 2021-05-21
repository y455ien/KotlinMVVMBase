package com.example.kotlinmvvmbase.util.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding
import com.example.kotlinmvvmbase.modules.business.ui.profile.EvenNumberModel
import com.example.kotlinmvvmbase.modules.business.ui.profile.ItemViewModel

class CustomAdapter(private var data: List<ItemViewModel>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.MyViewHolder {
        val viewBinding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding)
    }

//    override fun getItemViewType(position: Int): Int {
////        return super.getItemViewType(position)
//        when (data[position]) {
//            is EvenNumberModel -> return RecyclerViewItemBinding()
//        }
//    }

    override fun onBindViewHolder(holder: CustomAdapter.MyViewHolder, position: Int) {
        val model = data[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(private val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ItemViewModel) {
            binding.recyclerViewItemText.text = model.getData().toString()
        }
    }
}