package com.creatinapps.fitnescoach

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creatinapps.fitnescoach.databinding.CreatinDiaryItemBinding
import java.io.File

class MealsAdapter : ListAdapter<FitMeal, MealsAdapter.FitMealHolder>(
    object : DiffUtil.ItemCallback<FitMeal>() {
        override fun areItemsTheSame(oldItem: FitMeal, newItem: FitMeal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FitMeal, newItem: FitMeal): Boolean {
            return oldItem == newItem
        }
    }
) {
    class FitMealHolder(
        val binding: CreatinDiaryItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FitMealHolder {
        return FitMealHolder(
            CreatinDiaryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FitMealHolder, position: Int) {
        val meal = getItem(position)
        val context = holder.itemView.context
        holder.binding.apply {
            this.fatsTv.text = String.format(
                context.getString(R.string.fats_s_g),
                meal.fat,
            )
            this.carbsTv.text = String.format(
                context.getString(R.string.carbs_s_g),
                meal.carbs,
            )
            this.proteinTv.text = String.format(
                context.getString(R.string.protein_s_g),
                meal.protein,
            )
            this.nameTv.text = meal.name
            this.caloriesTv.text = String.format(
                context.getString(R.string.calories_s),
                meal.calories
            )
            if (meal.imageFile.isNotEmpty()) {
                val file = File(meal.imageFile)
                if (file.exists()) {
                    val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    this.mealIv.setImageBitmap(bitmap)
                }
            }
        }
    }

}