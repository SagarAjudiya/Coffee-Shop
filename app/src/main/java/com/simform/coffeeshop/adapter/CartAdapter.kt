package com.simform.coffeeshop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simform.coffeeshop.databinding.ItemCoffeeBinding
import com.simform.coffeeshop.model.Coffee

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartList = Coffee.cartItemList

    class CartViewHolder(val binding: ItemCoffeeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coffeeCart: Coffee) {
            binding.coffee = coffeeCart
            binding.btnAdd.visibility = View.INVISIBLE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val holder = CartViewHolder(
            ItemCoffeeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return holder
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }

}