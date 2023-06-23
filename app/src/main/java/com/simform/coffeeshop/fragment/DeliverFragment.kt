package com.simform.coffeeshop.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simform.coffeeshop.R
import com.simform.coffeeshop.activity.DeliveryActivity
import com.simform.coffeeshop.databinding.FragmentDeliverBinding

class DeliverFragment : Fragment() {

    private lateinit var binding: FragmentDeliverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeliverBinding.inflate(layoutInflater)

        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.apply {
            var count = Integer.parseInt(tvCount.text.toString())
            cvOrder.setBackgroundResource(R.drawable.card_corner)

            // Button call Tapped
            btnOrder.setOnClickListener {
                startActivity(Intent(activity, DeliveryActivity::class.java))
            }

            btnPlus.setOnClickListener {
                count += 1
                tvCount.text = count.toString()
            }

            btnMinus.setOnClickListener {
                if(count != 0) {
                    count -= 1
                    tvCount.text = count.toString()
                }
            }
        }
    }

}