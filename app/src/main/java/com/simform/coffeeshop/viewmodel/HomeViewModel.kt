package com.simform.coffeeshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.coffeeshop.model.CoffeeModel
import com.simform.coffeeshop.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: Repository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getCoffee()
        }
    }

    val coffee: LiveData<List<CoffeeModel>>
        get() = homeRepository.coffee
}