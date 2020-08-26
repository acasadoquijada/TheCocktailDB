package com.example.thecocktaildb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.databinding.DrinkInListBinding
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.ui.util.OnClickElementInterface
import com.squareup.picasso.Picasso

class DrinkAdapter() : RecyclerView.Adapter<DrinkAdapter.DrinkHolder>(){

    var drinkList: List<Drink> = ArrayList()
    lateinit var mItemClickListener: OnClickElementInterface

    constructor(mItemClickListener: OnClickElementInterface) : this() {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = DrinkInListBinding.inflate(inflater,parent,false)

        return DrinkHolder(binding)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    fun setDrinks(drinkList: List<Drink>){

        updateDrinks(drinkList)

        notifyDataSetChanged()
    }

    private fun updateDrinks(drinkList: List<Drink>){
        this.drinkList = drinkList
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {

        drinkList[position].name?.let { drinkList[position].image?.let { it1 ->
            holder.bind(it1, it)
        }
        }
    }

    inner class DrinkHolder(binding: DrinkInListBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val binding:DrinkInListBinding

        init{
            this.binding = binding
            this.binding.root.setOnClickListener(this)
        }

        fun bind(image: String, name: String){
            setImage(image)
            setName(name)
        }

        private fun setImage(image:String){
            Picasso.get().load(image).into(binding.image)
        }

        private fun setName(name: String){
            binding.name.text = name
        }

        override fun onClick(v: View?) {
            mItemClickListener.onItemClick(adapterPosition)
        }
    }
}