package com.simform.coffeeshop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.coffeeshop.data.model.CoffeeList
import com.simform.coffeeshop.databinding.ItemCoffeeCartBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartList = ArrayList<CoffeeList>()
    var onItemDelete: ((CoffeeList) -> Unit)? = null

    class CartViewHolder(val binding: ItemCoffeeCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coffeeCart: CoffeeList) {
            binding.coffee = coffeeCart
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val holder = CartViewHolder(
            ItemCoffeeCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        holder.binding.btnDelete.setOnClickListener {
            onItemDelete?.let {
                it(cartList[holder.adapterPosition])
            }
        }

        return holder
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<CoffeeList>) {
        cartList = list
        notifyDataSetChanged()
    }

}