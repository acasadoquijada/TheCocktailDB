package com.example.thecocktaildb.model.drink

import com.google.gson.annotations.SerializedName

class DrinkList{

    @SerializedName("drinks")
    var list: MutableList<Drink> = ArrayList()
}