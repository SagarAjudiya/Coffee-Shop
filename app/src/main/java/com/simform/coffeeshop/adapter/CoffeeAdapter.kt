package com.simform.coffeeshop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.coffeeshop.data.model.CoffeeList
import com.simform.coffeeshop.databinding.ItemCoffeeBinding

class CoffeeAdapter : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    private var coffeeList = ArrayList<CoffeeList>()
    var onItemClick: ((CoffeeList) -> Unit)? = null
    var onItemAdd: ((CoffeeList) -> Unit)? = null

    class CoffeeViewHolder(val binding: ItemCoffeeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coffee: CoffeeList) {
            binding.coffee = coffee
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val holder = CoffeeViewHolder(
            ItemCoffeeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        // Click on item
        holder.itemView.setOnClickListener {
            onItemClick?.let {
                it(coffeeList[holder.adapterPosition])
            }
        }

        // Click on Add button
        holder.binding.btnAdd.setOnClickListener {
            onItemAdd?.let {
                it(coffeeList[holder.adapterPosition])
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.bind(coffeeList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<CoffeeList>) {
        coffeeList = list
        notifyDataSetChanged()
    }

}