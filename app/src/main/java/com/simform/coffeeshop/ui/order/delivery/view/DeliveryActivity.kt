package com.simform.coffeeshop.ui.order.delivery.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simform.coffeeshop.R
import com.simform.coffeeshop.databinding.ActivityDeliveryBinding

class DeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        binding.apply {
            cvDelivery.setBackgroundResource(R.drawable.card_corner)

            // Set Toolbar
            toolBar.tvTitle.text = ""
            toolBar.imgLeft.setOnClickListener { finish() }

            // Call Button Tapped
            btnCall.setOnClickListener {
                val callUri = Uri.parse("tel:" + "777")
                startActivity(Intent(Intent.ACTION_DIAL, callUri))
            }
        }
    }

}