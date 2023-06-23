package com.simform.coffeeshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simform.coffeeshop.databinding.FragmentPickupBinding

class PickupFragment : Fragment() {

    private lateinit var binding: FragmentPickupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPickupBinding.inflate(layoutInflater)

        initViews()
        return binding.root
    }

    /**
     * Init Views
     */
    private fun initViews() {

    }

}