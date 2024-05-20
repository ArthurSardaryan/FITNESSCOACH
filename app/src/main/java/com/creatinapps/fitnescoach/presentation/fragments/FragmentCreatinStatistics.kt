package com.creatinapps.fitnescoach.presentation.fragments

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.creatinapps.fitnescoach.R
import com.creatinapps.fitnescoach.databinding.FragmentCreatinStatisticsBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

private const val NAME_EXTRA = "name"
private const val IMAGE_URI_EXTRA = "imageUri"
const val BURNED_EXTRA = "burned"
const val EARNED_EXTRA = "earned"
const val MEALS_EXTRA = "meals"
const val WORKOUT_EXTRA = "workouts"

class FragmentCreatinStatistics : Fragment() {
    private val binding by lazy {
        FragmentCreatinStatisticsBinding.inflate(layoutInflater)
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
            binding.personIv.setImageURI(it)
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
        sharedPrefs.edit().putString(IMAGE_URI_EXTRA, imageUri).apply()
        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }
        catch (_: IOException) {
        }
        return file
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        imageUri = sharedPrefs.getString(IMAGE_URI_EXTRA, null)
        if (imageUri != null) {
            val file = File(imageUri!!)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.personIv.setImageBitmap(bitmap)
            }
        }
        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.nameTv.setText(
            sharedPrefs.getString(
                NAME_EXTRA,
                ""
            ).orEmpty()
        )
        binding.nameTv.doOnTextChanged { text, _, _, _ ->
            val name = text.toString()
            sharedPrefs.edit().putString(NAME_EXTRA, name).apply()
        }
        binding.personIv.setOnClickListener {
            imageLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
        binding.burnedTv.text = String.format(
            getString(R.string.calories_burned_s),
            sharedPrefs.getInt(
                BURNED_EXTRA,
                0
            )
        )
        binding.mealsTv.text = String.format(
            getString(R.string.meals_n_s),
            sharedPrefs.getInt(
                MEALS_EXTRA,
                0
            )
        )
        binding.workoutsTv.text = String.format(
            getString(R.string.workouts_n_s),
            sharedPrefs.getInt(
                WORKOUT_EXTRA,
                0
            )
        )
        binding.earnedTv.text = String.format(
            getString(R.string.calories_earned_s),
            sharedPrefs.getInt(
                EARNED_EXTRA,
                0
            )
        )


        return binding.root
    }
}