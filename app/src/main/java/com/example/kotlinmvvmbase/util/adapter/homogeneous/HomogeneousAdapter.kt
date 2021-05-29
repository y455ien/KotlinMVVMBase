package com.example.kotlinmvvmbase.util.adapter.homogeneous

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvmbase.databinding.RecyclerViewItemBinding

class HomogeneousAdapter(private var data: List<Int>) : RecyclerView.Adapter<HomogeneousAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding)
    }
    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = data[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    inner class MyViewHolder(private val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Int) {
            binding.recyclerViewItemText.text = model.toString()
        }
    }
}