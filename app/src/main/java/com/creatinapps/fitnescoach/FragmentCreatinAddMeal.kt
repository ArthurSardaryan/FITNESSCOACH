package com.creatinapps.fitnescoach

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.creatinapps.fitnescoach.databinding.FragmentCreatinAddMealBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
const val DISHES_DIR_NAME = "dishes"

const val IMAGE_FORMAT = ".jpg"
class FragmentCreatinAddMeal: Fragment()  {
    private val binding by lazy {
        FragmentCreatinAddMealBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[AddMealViewModel::class.java]
    }
    private val sharedPrefs by lazy {
        requireContext()
            .getSharedPreferences(
                requireContext().packageName,
                Context.MODE_PRIVATE
            )
    }
    private var imageUri: String? = null
    @Suppress("DEPRECATION")
    private val imageLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia(),
    ) {
        if (it != null) {
            binding.mealIv.setImageURI(it)
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireActivity().contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
            else {
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
            }
            saveFileToInternalStorage(bitmap)
        }
    }
    private fun saveFileToInternalStorage(bitmap: Bitmap): File {
        val fileName = "${System.currentTimeMillis()}$IMAGE_FORMAT"
        val wrapper = ContextWrapper(requireContext())
        var file = wrapper.getDir(DISHES_DIR_NAME, Context.MODE_PRIVATE)
        file = File(file, fileName)
        imageUri = file.absolutePath
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (_: IOException) { }
        return file
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.mealIv.setOnClickListener {
            imageLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
        binding.buttonAdd.setOnClickListener {
            val name = binding.nameTv.text.toString()
            if (name.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.fill_all_fields_correctly),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            try {
                val calories = binding.caloriesTv.text.toString().toInt()
                val protein = binding.proteinTv.text.toString().toInt()
                val fats = binding.fatsTv.text.toString().toInt()
                val carbs = binding.carbsTv.text.toString().toInt()
                val image = imageUri.orEmpty()
                val meal = FitMeal(
                    name = name,
                    calories = calories,
                    protein = protein,
                    fat = fats,
                    carbs = carbs,
                    imageFile = image
                )
                val caloriesData = sharedPrefs.getInt(EARNED_EXTRA, 0)
                sharedPrefs.edit().putInt(EARNED_EXTRA, caloriesData + calories).apply()
                val mealsData = sharedPrefs.getInt(MEALS_EXTRA, 0)
                sharedPrefs.edit().putInt(MEALS_EXTRA, mealsData + 1).apply()
                viewModel.addMeal(meal)
                parentFragmentManager.popBackStack()
            }
            catch (_: Exception) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.fill_all_fields_correctly),
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }
        return binding.root
    }
}