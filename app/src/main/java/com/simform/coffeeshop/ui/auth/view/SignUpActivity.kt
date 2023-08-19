package com.simform.coffeeshop.ui.auth.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.simform.coffeeshop.ui.dashboard.view.HomeActivity
import com.simform.coffeeshop.databinding.ActivitySignUpBinding
import com.simform.coffeeshop.helper.URLConstant
import com.simform.coffeeshop.ui.auth.viewmodel.SignupViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var vm: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /**
     * Init Views
     */
    private fun initViews() {
        vm = ViewModelProvider(this@SignUpActivity).get(SignupViewModel::class.java)
        binding.apply {
            userName = etUsername.text.toString()
            email = etEmail.text.toString()
            password = etPassword.text.toString()

            btnSignUp.setOnClickListener {
                registerUser()
            }

            tvAlreadySignup.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    /**
     * Register User
     */
    private fun registerUser() {
        vm.registerUser(
            binding.etEmail.text.toString(), binding.etPassword.text.toString()
        ) { userResponse ->
            if (userResponse.token == URLConstant.AUTH_TOKEN) {
                startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
            }
        }

    }

}