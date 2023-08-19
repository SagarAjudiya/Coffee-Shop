package com.simform.coffeeshop.ui.auth.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simform.coffeeshop.R
import com.simform.coffeeshop.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        binding.apply {
            tvNoAccount.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                finish()
            }
        }
    }

}