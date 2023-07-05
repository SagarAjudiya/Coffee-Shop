package com.simform.coffeeshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simform.coffeeshop.activity.SignUpActivity
import com.simform.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    /**
     *  Init Views
     */
    private fun initViews() {
        binding.apply {
            // Start Button Tapped
            btnStart.setOnClickListener {
                startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
            }
        }
    }

}