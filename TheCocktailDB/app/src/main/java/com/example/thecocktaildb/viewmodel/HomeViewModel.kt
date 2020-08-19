package com.example.thecocktaildb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.repository.Repository

class HomeViewModel : ViewModel() {

    private var repository: Repository = Repository()

    fun getRandomCocktail(newCocktail: Boolean = false): MutableLiveData<DrinkList>{
        return repository.getRandomCocktail(newCocktail)
    }

    fun getDrinkList(query: String): MutableLiveData<DrinkList>{
        return repository.getDrinkList(query)
    }
}