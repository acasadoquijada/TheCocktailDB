package com.example.thecocktaildb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.repository.Repository

class HomeViewModel : ViewModel() {

    var repository: Repository = Repository()

    fun getRandomCocktail(): MutableLiveData<DrinkList>{
        return repository.getRandomCocktail()
    }

}