package com.simform.coffeeshop.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.simform.coffeeshop.R
import com.simform.coffeeshop.data.model.Coffee
import com.simform.coffeeshop.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        binding.apply {
            // Set ToolBar
            toolBar.imgRight.setImageResource(R.drawable.heart)
            toolBar.tvTitle.text = getString(R.string.detail)
            toolBar.imgLeft.setOnClickListener { finish() }

            cvBuy.setBackgroundResource(R.drawable.card_corner)

            // Call Button Tapped
            btnBuyNow.setOnClickListener {
                startActivity(Intent(this@ItemDetailActivity, OrderActivity::class.java))
            }
        }

        // Get Item details from Home
        val coffee = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("coffee", Coffee::class.java)
        } else {
            intent.getParcelableExtra<Coffee>("coffee")
        }

        if (coffee != null) {
            binding.apply {
                imgCoffee.setImageResource(coffee.coffeeImage)
                tvCoffeeName.text = coffee.coffeeName
                tvWith.text = coffee.coffeeWith
                tvRating.text = coffee.rating
                tvRatingCount.text = coffee.ratingCount
                tvDesc.text = coffee.desc
                tvPriceCount.text = coffee.price
            }
        }

        val desc = binding.tvDesc.text.toString()
        setReadMore(desc)
    }

    /**
     * Set Read More Span
     */
    private fun setReadMore(text: String) {
        val readMore = getString(R.string.read_more)
        if (text.length > 150) {
            val subText = SpannableString(text.substring(0..150) + "..." + readMore)

            val moreSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    binding.tvDesc.text = text
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = getColor(R.color.orange)
                    ds.isUnderlineText = false
                }
            }
            subText.setSpan(moreSpan, 154, 154 + readMore.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.tvDesc.text = subText
            binding.tvDesc.movementMethod = LinkMovementMethod.getInstance()
        }
    }

}