package com.example.thecocktaildb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.repository.Repository

class ViewModel : ViewModel() {

    private var repository: Repository = Repository()

    fun getDrinkList(query: String): MutableLiveData<List<Drink>>{
        return repository.getDrinkList(query)
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

}