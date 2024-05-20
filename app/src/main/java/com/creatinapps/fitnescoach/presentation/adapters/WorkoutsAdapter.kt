package com.creatinapps.fitnescoach.presentation.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creatinapps.fitnescoach.R
import com.creatinapps.fitnescoach.databinding.CreatinTrainingsItemBinding
import com.creatinapps.fitnescoach.domain.model.FitWorkout
import java.io.File
import javax.inject.Inject

class WorkoutsAdapter @Inject constructor()
    : ListAdapter<FitWorkout, WorkoutsAdapter.FitWorkoutHolder>(
    object : DiffUtil.ItemCallback<FitWorkout>() {
        override fun areItemsTheSame(oldItem: FitWorkout, newItem: FitWorkout): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FitWorkout, newItem: FitWorkout): Boolean {
            return oldItem == newItem
        }
    }
) {
    class FitWorkoutHolder(
        val binding: CreatinTrainingsItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitWorkoutHolder {
        return FitWorkoutHolder(
            CreatinTrainingsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FitWorkoutHolder, position: Int) {
        val workout = getItem(position)
        val context = holder.itemView.context
        holder.binding.apply {
            this.nameTv.text = workout.name
            if (workout.imageFile.isNotEmpty()) {
                val file = File(workout.imageFile)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    this.trainingsIv.setImageBitmap(bitmap)
                }
            }
            this.setsTv.text = String.format(
                context.getString(R.string.sets_s),
                workout.sets
            )
            this.repsTv.text = String.format(
                context.getString(R.string.reps_s),
                workout.reps
            )
            this.workoutTypeTv.text = workout.type
            this.caloriesTv.text = String.format(
                context.getString(R.string.calories_burned_s),
                workout.reps * workout.sets
            )
        }
    }

}