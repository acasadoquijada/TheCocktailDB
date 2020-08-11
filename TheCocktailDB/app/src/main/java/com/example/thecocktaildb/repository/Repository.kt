package com.example.thecocktaildb.repository

import android.R.attr.apiKey
import android.R.attr.author
import androidx.lifecycle.MutableLiveData
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.controllers.DrinkController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository() {

    var randomDrink: MutableLiveData<DrinkList> = MutableLiveData()

    val drinkController: DrinkController = DrinkController()


    fun getRandomCocktail(): MutableLiveData<DrinkList> {

        drinkController.getRandomDrinkCall().enqueue(object : Callback<DrinkList?> {

            override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {
                if(response.isSuccessful){
                    randomDrink.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
                val drinkList = DrinkList()
                randomDrink.postValue(drinkList)
            }
        })

        return randomDrink
    }

}