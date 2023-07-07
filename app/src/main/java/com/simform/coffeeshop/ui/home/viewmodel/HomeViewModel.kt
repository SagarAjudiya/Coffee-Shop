package com.simform.coffeeshop.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simform.coffeeshop.data.model.CoffeeModel
import com.simform.coffeeshop.repository.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private var liveData = MutableLiveData<List<CoffeeModel>>()

    val coffee: LiveData<List<CoffeeModel>>
        get() = liveData

    fun getCoffee() {
        homeRepository.getCoffee {
            liveData.postValue(it)
        }
        homeRepository.getData()
    }

}