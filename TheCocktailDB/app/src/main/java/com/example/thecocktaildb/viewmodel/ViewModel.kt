package com.example.thecocktaildb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.repository.Repository

class ViewModel : ViewModel() {

    private var repository: Repository = Repository()

    fun getDrinkList(query: String): MutableLiveData<List<Drink>>{
        return repository.getDrinkList(query)
    }

    fun getDrink(newCocktail: Boolean = false, drinkId: Long = -1): MutableLiveData<Drink>{

        val test: Long = -1    // This is a placeholder. Needs to be changed

        if(drinkId != test){
            return repository.getDrink(drinkId)
        }

        return repository.getRandomCocktail(newCocktail)
    }

    fun getDrinkId(position: Int): Long{
        return repository.getDrinkPosition(position)
    }
}