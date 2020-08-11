package com.example.thecocktaildb.model.drink

import com.google.gson.annotations.SerializedName

class DrinkList{
    operator fun invoke(drink: Drink) {

    }

    @SerializedName("drinks")
    var drinkList: MutableList<Drink> = mutableListOf()
}