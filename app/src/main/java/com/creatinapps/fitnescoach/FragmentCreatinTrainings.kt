package com.creatinapps.fitnescoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.creatinapps.fitnescoach.databinding.FragmentCreatinTrainingsBinding

class FragmentCreatinTrainings: Fragment() {
    private val binding by lazy {
        FragmentCreatinTrainingsBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        WorkoutsAdapter()
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[FitWorkoutsViewModel::class.java]
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
            navigateTo(FragmentCreatinAddWorkout())
        }
        binding.rvTrainings.adapter = adapter
        viewModel.getWorkouts().observe(viewLifecycleOwner) {
            adapter.submitList(it.reversed())
        }
        return binding.root
    }
}