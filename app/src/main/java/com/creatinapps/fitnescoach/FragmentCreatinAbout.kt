package com.creatinapps.fitnescoach

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.creatinapps.fitnescoach.databinding.FragmentCreatinAboutBinding

class FragmentCreatinAbout: Fragment() {
    private val binding by lazy {
        FragmentCreatinAboutBinding.inflate(layoutInflater)
    }
    private val appLink by lazy {
        "https://play.google.com/store/apps/details?id=${requireContext().packageName}"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.buttonRate.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
            }
            sendIntent.data = Uri.parse(appLink)
            startActivity(sendIntent)
        }
        binding.buttonShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
            }
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Check this app at $appLink"
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
        return binding.root
    }
}