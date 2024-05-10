package com.creatinapps.fitnescoach

import androidx.fragment.app.Fragment

fun Fragment.navigateTo(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .replace(R.id.container_yellow, fragment)
        .addToBackStack(null)
        .commit()
}