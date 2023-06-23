package com.simform.coffeeshop.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.simform.coffeeshop.R
import com.simform.coffeeshop.activity.ItemDetailActivity
import com.simform.coffeeshop.adapter.CoffeeAdapter
import com.simform.coffeeshop.adapter.HomeVPAdapter
import com.simform.coffeeshop.databinding.FragmentHomeBinding
import com.simform.coffeeshop.decoration.CoffeeDecoration
import com.simform.coffeeshop.model.Coffee

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var coffeeAdapter: CoffeeAdapter
    private lateinit var coffeeList: ArrayList<Coffee>
    private lateinit var searchList: ArrayList<Coffee>
    private var cartList = ArrayList<Coffee>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        initViews()
        return binding.root
    }

    /**
     * Init Views
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
        // Set Images for ViewPager
        val images = listOf(
            R.drawable.imagvp,
            R.drawable.imagvp,
            R.drawable.imagvp,
            R.drawable.imagvp,
            R.drawable.imagvp,
        )
        val adapterVP = HomeVPAdapter(images)
        binding.viewPager.adapter = adapterVP

        coffeeList = Coffee.coffeeList
        searchList = coffeeList
        coffeeAdapter = CoffeeAdapter()

        // Set RecyclerView
        binding.rvHome.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = coffeeAdapter
            addItemDecoration(CoffeeDecoration())
        }

        // Click event on item
        coffeeAdapter.onItemClick = {
            val intent = Intent(activity, ItemDetailActivity::class.java)
            intent.putExtra("coffee", it)
            startActivity(intent)
        }

        // Click event on Button Add Cart
        coffeeAdapter.onItemAdd = {
            cartList.add(it)
            addToCart()
            Toast.makeText(activity, getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
        }

        // Search for items
        binding.etSearch.addTextChangedListener {
            searchCoffee(it.toString())
        }

        // Filter data on Chip is Select
        binding.cpAll.setOnClickListener {
            coffeeAdapter.submitList(searchList)
            binding.rvHome.adapter?.notifyDataSetChanged()
        }
        binding.cpCappuccino.setOnClickListener {
            filterCoffee(getString(R.string.cappuccino))
        }
        binding.cpMachiato.setOnClickListener {
            filterCoffee(getString(R.string.machiato))
        }
        binding.cpLatte.setOnClickListener {
            filterCoffee(getString(R.string.latte))
        }
        binding.cpAmericano.setOnClickListener {
            filterCoffee(getString(R.string.americano))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterCoffee(text: String) {
        val filterList = searchList.filter {
            it.coffeeName.lowercase().contains(text.lowercase())
        } as ArrayList<Coffee>
        coffeeAdapter.submitList(filterList)
        binding.rvHome.adapter?.notifyDataSetChanged()
    }

    // Search in Lists
    @SuppressLint("NotifyDataSetChanged")
    private fun searchCoffee(query: String) {
        searchList = coffeeList.filter {
            it.coffeeName.lowercase().contains(query.lowercase())
        } as ArrayList<Coffee>
        coffeeAdapter.submitList(searchList)
        binding.rvHome.adapter?.notifyDataSetChanged()
    }

    // Add Items in cart
    private fun addToCart() {
        val bundle = Bundle()
        bundle.putParcelableArrayList("cart", cartList)
        setFragmentResult("REQ_KEY", bundle)
    }

}