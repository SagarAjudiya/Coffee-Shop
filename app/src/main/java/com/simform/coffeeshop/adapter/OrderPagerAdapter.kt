package com.simform.coffeeshop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simform.coffeeshop.ui.order.delivery.view.DeliverFragment
import com.simform.coffeeshop.ui.order.pickup.PickupFragment

class OrderPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    enum class Order {
        DELIVER,
        PICKUP,
    }

    override fun getItemCount(): Int {
        return Order.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (Order.values()[position]) {
            Order.DELIVER -> DeliverFragment()
            Order.PICKUP -> PickupFragment()
        }
    }

}