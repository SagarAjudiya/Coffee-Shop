package com.simform.coffeeshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.coffeeshop.databinding.ItemHomeVpBinding

class HomeVPAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<HomeVPAdapter.HomeVPViewHolder>() {

    inner class HomeVPViewHolder(private val binding: ItemHomeVpBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imgVP.setImageResource(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVPViewHolder {
        val holder = HomeVPViewHolder(
            ItemHomeVpBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: HomeVPViewHolder, position: Int) {
        holder.bind(images[position])
    }

}