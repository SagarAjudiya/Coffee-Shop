package com.simform.coffeeshop.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.simform.coffeeshop.data.model.UserResponse
import com.simform.coffeeshop.repository.SignupRepository

class SignupViewModel : ViewModel() {

    fun registerUser(email: String, password: String, callback: (UserResponse) -> Unit) {
        val signupRepository = SignupRepository()
        signupRepository.registerUser(email, password) {
            callback(it)
        }
    }

}