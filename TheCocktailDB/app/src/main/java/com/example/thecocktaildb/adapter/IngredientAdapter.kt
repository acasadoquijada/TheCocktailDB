package com.example.thecocktaildb.adapter

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.databinding.IngredientBinding
import com.example.thecocktaildb.model.Ingredient

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {

    private var ingredientList: List<Ingredient> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = IngredientBinding.inflate(inflater,parent,false)

        return IngredientHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun setIngredients(ingredientList: List<Ingredient>){
        updateIngredients(ingredientList)
        notifyDataSetChanged()
    }

    private fun updateIngredients(ingredientList: List<Ingredient>){
        this.ingredientList = ingredientList
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.bind(ingredientList[position].name, ingredientList[position].measure)
    }


    inner class IngredientHolder(binding: IngredientBinding):RecyclerView.ViewHolder(binding.root) {

        private val binding: IngredientBinding

        init {
            this.binding = binding
        }

        fun bind(name: String, measure: String) {
            setName(name)
            setMeasure(measure)
        }

        private fun setName(name: String) {
            binding.name.text = name
        }

        private fun setMeasure(measure: String) {
            binding.measure.text = measure
        }
    }
}