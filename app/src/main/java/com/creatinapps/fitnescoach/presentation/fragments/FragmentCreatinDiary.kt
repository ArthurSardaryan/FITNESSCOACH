package com.creatinapps.fitnescoach.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.creatinapps.fitnescoach.databinding.FragmentCreatinDiaryBinding
import com.creatinapps.fitnescoach.presentation.adapters.MealsAdapter
import com.creatinapps.fitnescoach.presentation.viewmodel.FitMealsViewModel
import com.creatinapps.fitnescoach.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCreatinDiary: Fragment()  {
    private val binding by lazy {
        FragmentCreatinDiaryBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var adapter : MealsAdapter
    private val viewModel by viewModels<FitMealsViewModel>()
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDishes().observe(viewLifecycleOwner) {
            adapter.submitList(it.reversed())
        }
    }
}