package com.example.thecocktaildb.viewmodel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.example.thecocktaildb.R
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.repository.Repository


class ViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: Repository = Repository()
    private val categoryList: MutableList<String> = ArrayList()
    var query: String = ""

    init{
        val context = getApplication<Application>().applicationContext
        categoryList.add(context.getString(R.string.category_alcohol))
        categoryList.add(context.getString(R.string.category_no_alcohol))
        categoryList.add(context.getString(R.string.category_ordinary_drink))
        categoryList.add(context.getString(R.string.category_cocktail))
        categoryList.add(context.getString(R.string.category_cocktail_glass))
        categoryList.add(context.getString(R.string.category_champagne_flute))
    }


    fun getDrinkList(query: String): MutableLiveData<List<Drink>>{
        return repository.getDrinkList(query)
    }

    fun getDrinkListSize(): Int{
        return repository.getDrinkListSize()
    }

    fun getDrink(drinkId: Long = -1L): MutableLiveData<Drink>{
        return repository.getDrink(drinkId)
    }

    fun getDrinkId(position: Int): Long{
        return repository.getDrinkId(position)
    }

    fun updateRandomDrink(){
        repository.updateRandomDrink()
    }

    fun getCategories(): List<String>{
        return categoryList
    }

    fun getCategoryName(position: Int): String{
        return categoryList[position]
    }



}