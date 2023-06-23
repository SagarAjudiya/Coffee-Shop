package com.simform.coffeeshop.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.simform.coffeeshop.R
import com.simform.coffeeshop.adapter.OrderPagerAdapter
import com.simform.coffeeshop.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var orderPagerAdapter: OrderPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        orderPagerAdapter = OrderPagerAdapter(supportFragmentManager, lifecycle)

        binding.apply {
            viewPager.adapter = orderPagerAdapter

            // Set ToolBar
            toolBar.imgLeft.setOnClickListener { finish() }
            toolBar.imgRight.visibility = View.GONE
            toolBar.tvTitle.setText(R.string.order)

            // Connect TabLayout and ViewPager2
            TabLayoutMediator(tbLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.deliver)
                    1 -> tab.text = getString(R.string.pick_up)
                }
            }.attach()

        }

    }

}