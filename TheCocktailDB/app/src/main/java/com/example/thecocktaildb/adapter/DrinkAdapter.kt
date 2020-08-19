package com.example.thecocktaildb.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.databinding.DrinkInListBinding
import com.example.thecocktaildb.databinding.IngredientBinding
import com.example.thecocktaildb.model.Ingredient
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.model.drink.DrinkList
import com.squareup.picasso.Picasso

class DrinkAdapter : RecyclerView.Adapter<DrinkAdapter.DrinkHolder>(){

    var drinkList: List<Drink> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = DrinkInListBinding.inflate(inflater,parent,false)

        return DrinkHolder(binding)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }



    fun setDrinks(drinkList: List<Drink>){
        this.drinkList = drinkList

        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {

        drinkList[position].name?.let { drinkList[position].image?.let { it1 ->
            holder.bind(it1, it)
        }
        }
    }

    class DrinkHolder(binding: DrinkInListBinding):RecyclerView.ViewHolder(binding.root){

        private val binding:DrinkInListBinding

        init{
            this.binding = binding
        }

        fun bind(image: String, name: String){
            Log.d("TEST__", "name: $name - image: $image")

            Picasso.get().load(image).into(binding.image)
            binding.name.text = name

        }
    }
}