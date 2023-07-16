package com.simform.coffeeshop.ui.cart.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.simform.coffeeshop.adapter.CartAdapter
import com.simform.coffeeshop.data.model.CoffeeList
import com.simform.coffeeshop.databinding.FragmentCartBinding
import com.simform.coffeeshop.decoration.CoffeeDecoration

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private var cartItem = CoffeeList.cartItemList
    private var cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        binding.animCart.visibility = View.GONE
        if (cartItem.isNotEmpty()) {
            binding.animCart.visibility = View.GONE
        } else {
            binding.animCart.visibility = View.VISIBLE
        }

        // get Add Items from Home
        setFragmentResultListener("REQ_KEY") { _, bundle ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelableArrayList("cart", CoffeeList::class.java)?.let {
                    cartItem.addAll(it)
                }
            } else {
                cartItem.addAll(bundle.getParcelableArrayList("cart") ?: ArrayList())
            }
        }

        binding.rvCart.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = cartAdapter
            addItemDecoration(CoffeeDecoration())
        }
        cartAdapter.submitList(cartItem)
    }

}