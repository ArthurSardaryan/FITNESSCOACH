package com.creatinapps.fitnescoach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.creatinapps.fitnescoach.databinding.FragmentCreatinMenuBinding

class FragmentCreatinMenu: Fragment() {
    private val binding by lazy {
        FragmentCreatinMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.diaryCv.setOnClickListener {
            navigateTo(FragmentCreatinDiary())
        }
        binding.diaryIv.setOnClickListener {
            navigateTo(FragmentCreatinDiary())
        }
        binding.statisticsIv.setOnClickListener {
            navigateTo(FragmentCreatinStatistics())
        }
        binding.trainingsIv.setOnClickListener {
            navigateTo(FragmentCreatinTrainings())
        }
        binding.statisticsCv.setOnClickListener {
            navigateTo(FragmentCreatinStatistics())
        }
        binding.trainingsCv.setOnClickListener {
            navigateTo(FragmentCreatinTrainings())
        }
        binding.aboutCv.setOnClickListener {
            navigateTo(FragmentCreatinAbout())
        }
        binding.aboutIv.setOnClickListener {
            navigateTo(FragmentCreatinAbout())
        }
        return binding.root
    }
}
