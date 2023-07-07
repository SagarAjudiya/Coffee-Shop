package com.simform.coffeeshop.ui.home.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.simform.coffeeshop.R
import com.simform.coffeeshop.adapter.CoffeeAdapter
import com.simform.coffeeshop.adapter.HomeVPAdapter
import com.simform.coffeeshop.data.model.CoffeeList
import com.simform.coffeeshop.databinding.FragmentHomeBinding
import com.simform.coffeeshop.decoration.CoffeeDecoration
import com.simform.coffeeshop.network.ApiClient
import com.simform.coffeeshop.network.ApiInterface
import com.simform.coffeeshop.repository.HomeRepository
import com.simform.coffeeshop.ui.home.viewmodel.HomeVMFactory
import com.simform.coffeeshop.ui.home.viewmodel.HomeViewModel
import com.simform.coffeeshop.ui.itemdetail.view.ItemDetailActivity
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var coffeeAdapter = CoffeeAdapter()
    private var coffeeList = ArrayList<CoffeeList>()
    private var searchList = coffeeList
    private var cartList = ArrayList<CoffeeList>()
    private var coffeeTypeList = CoffeeList.coffeeTypeList
    private val sliderHandler = Handler()

    private lateinit var vm: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        initViews()
        initData()
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
        onInfinitePageChangeCallback(binding.viewPager, images.size)

//        coffeeList = Coffee.coffeeList
//        searchList = coffeeList
//        coffeeAdapter = CoffeeAdapter()

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

    /**
     * Infinite ViewPager
     */
    private fun onInfinitePageChangeCallback(viewPager2: ViewPager2, listSize: Int) {
        viewPager2.apply {
            clipChildren = false
            clipToPadding = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.85f + r * 0.14f)
        }
        viewPager2.setPageTransformer(transformer)

        val sliderRunnable = Runnable {
            kotlin.run {
                viewPager2.currentItem = binding.viewPager.currentItem + 1
            }
        }

        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    when (viewPager2.currentItem) {
                        listSize - 1 -> viewPager2.setCurrentItem(1, false)
                        0 -> viewPager2.setCurrentItem(listSize - 2, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 2000)

                // for indicator dots
                if (position != 0 && position != listSize - 1) {
                    // pageIndicatorView.setSelected(position-1)
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterCoffee(text: String) {
        val filterList = searchList.filter {
            it.coffeeName?.lowercase()?.contains(text.lowercase()) ?: false
        } as ArrayList<CoffeeList>
        coffeeAdapter.submitList(filterList)
        binding.rvHome.adapter?.notifyDataSetChanged()
        if (filterList.isEmpty()) {
            Toast.makeText(context, getString(R.string.coffee_not_available), Toast.LENGTH_SHORT)
                .show()
        }
    }

    // Search in Lists
    @SuppressLint("NotifyDataSetChanged")
    private fun searchCoffee(query: String) {
        searchList = coffeeList.filter {
            it.coffeeName?.lowercase()?.contains(query.lowercase()) ?: false
        } as ArrayList<CoffeeList>
        coffeeAdapter.submitList(searchList)
        binding.rvHome.adapter?.notifyDataSetChanged()
        if (searchList.isEmpty()) {
            Toast.makeText(context, getString(R.string.item_not_found), Toast.LENGTH_SHORT).show()
        }
    }

    // Add Items in cart
    private fun addToCart() {
        val bundle = Bundle()
        bundle.putParcelableArrayList("cart", cartList)
        setFragmentResult("REQ_KEY", bundle)
    }

    /**
     * Set data using API
     */
    private fun initData() {
        val apiInterface = ApiClient.retrofit.create(ApiInterface::class.java)
        val repository = HomeRepository(apiInterface)
        vm = ViewModelProvider(this, HomeVMFactory(repository)).get(HomeViewModel::class.java)

        vm.getCoffee()
        vm.coffee.observe(viewLifecycleOwner) { coffeeModelList ->
            coffeeModelList.forEach { coffeeModel ->
                coffeeModel.list?.let { coffeeList.addAll(it) }
                coffeeModel.coffeeType?.let { coffeeTypeList.add(it) }
            }
            coffeeAdapter.submitList(coffeeList)
            Log.d("TAG", "initData: $coffeeTypeList")
        }

    }

}