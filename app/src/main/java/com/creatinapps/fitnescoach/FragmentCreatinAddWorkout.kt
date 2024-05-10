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
import com.creatinapps.fitnescoach.databinding.FragmentCreatinAddWorkoutBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class FragmentCreatinAddWorkout: Fragment()  {
    private val binding by lazy {
        FragmentCreatinAddWorkoutBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[AddWorkoutViewModel::class.java]
    }
    private var imageUri: String? = null
    @Suppress("DEPRECATION")
    private val imageLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia(),
    ) {
        if (it != null) {
            binding.trainingIv.setImageURI(it)
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
    private val sharedPrefs by lazy {
        requireContext()
            .getSharedPreferences(
                requireContext().packageName,
                Context.MODE_PRIVATE
            )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.trainingIv.setOnClickListener {
            imageLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
        binding.buttonAdd.setOnClickListener {
            try {
                val name = binding.nameTv.text.toString()
                val type = binding.workoutTypeTv.text.toString()
                if (name.isEmpty() || type.isEmpty()) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.fill_all_fields_correctly),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                val sets = binding.setsTv.text.toString().toInt()
                val reps = binding.repsTv.text.toString().toInt()
                val imageFile = imageUri.orEmpty()
                val workout = FitWorkout(
                    name = name,
                    sets = sets,
                    reps = reps,
                    type = type,
                    imageFile = imageFile
                )
                val caloriesBurnedData = sharedPrefs.getInt(BURNED_EXTRA, 0)
                val caloriesBurnedThisTime = reps * sets
                val workouts = sharedPrefs.getInt(WORKOUT_EXTRA, 0)
                sharedPrefs.edit().putInt(WORKOUT_EXTRA, workouts + 1).apply()
                sharedPrefs.edit()
                    .putInt(BURNED_EXTRA, caloriesBurnedData + caloriesBurnedThisTime)
                    .apply()
                viewModel.addWorkout(workout)
                parentFragmentManager.popBackStack()
            }
            catch (_: Exception) { }

        }
        return binding.root
    }
}