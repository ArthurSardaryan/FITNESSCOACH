package com.creatinapps.fitnescoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.creatinapps.fitnescoach.databinding.FragmentCreatinDiaryBinding

class FragmentCreatinDiary: Fragment()  {
    private val binding by lazy {
        FragmentCreatinDiaryBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        MealsAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[FitMealsViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.buttonAdd.setOnClickListener {
            navigateTo(FragmentCreatinAddMeal())
        }
        binding.rvMeals.adapter = adapter
        viewModel.getDishes().observe(viewLifecycleOwner) {
            adapter.submitList(it.reversed())
        }
        return binding.root
    }
}