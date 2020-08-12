package com.example.thecocktaildb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.databinding.IngredientBinding
import com.example.thecocktaildb.model.Ingredient

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {

    var ingredientList: List<Ingredient> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = IngredientBinding.inflate(inflater,parent,false)

        return IngredientHolder(binding)
    }

    override fun getItemCount(): Int {

        ingredientList?.let { return ingredientList.size }
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        holder.bind(ingredientList[position].name, ingredientList[position].measure)
    }


    class IngredientHolder(binding: IngredientBinding):RecyclerView.ViewHolder(binding.root){

        val binding:IngredientBinding

        init{
            this.binding = binding
        }

        fun bind(name: String, measure:String){
            binding.name.text = name
            binding.measure.text = measure
        }

    }


}