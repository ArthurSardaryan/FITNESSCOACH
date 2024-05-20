package com.creatinapps.fitnescoach.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.creatinapps.fitnescoach.databinding.FragmentCreatinTrainingsBinding
import com.creatinapps.fitnescoach.presentation.adapters.WorkoutsAdapter
import com.creatinapps.fitnescoach.presentation.viewmodel.FitWorkoutsViewModel
import com.creatinapps.fitnescoach.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCreatinTrainings: Fragment() {
    private val binding by lazy {
        FragmentCreatinTrainingsBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var adapter: WorkoutsAdapter
    private val viewModel by viewModels<FitWorkoutsViewModel>()
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWorkouts().observe(viewLifecycleOwner) {
            adapter.submitList(it.reversed())
        }
    }
}