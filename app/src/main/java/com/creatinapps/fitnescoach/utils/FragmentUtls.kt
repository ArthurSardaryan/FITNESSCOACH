package com.creatinapps.fitnescoach.utils

import androidx.fragment.app.Fragment
import com.creatinapps.fitnescoach.R

fun Fragment.navigateTo(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .replace(R.id.container_yellow, fragment)
        .addToBackStack(null)
        .commit()
}