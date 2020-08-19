package com.example.thecocktaildb.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.controllers.MainController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository() {

    private var randomDrink: MutableLiveData<DrinkList> = MutableLiveData()

    private var drinkList: MutableLiveData<DrinkList> = MutableLiveData()

    private val mainController: MainController = MainController()

    fun getDrinkPosition(position: Int): Long{

        drinkList.value?.drinkList?.get(position)?.let {
            return it.id
        }

        return -1
    }

    fun getRandomCocktail(new:Boolean): MutableLiveData<DrinkList> {

        if(new || randomDrink.value == null){
            mainController.getRandomDrinkCall().enqueue(object : Callback<DrinkList?> {

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
        }

        return randomDrink
    }

    fun getDrinkList(query: String) : MutableLiveData<DrinkList>{

        val callback: Call<DrinkList> = when(query){
            "alcohol" -> mainController.getAlcoholicDrinksCall()
            "no alcohol" -> mainController.getNonAlcoholicDrinks()
            "ordinary drink" -> mainController.getOrdinaryDrinks()
            "cocktail" -> mainController.getCocktailsDrinks()
            "cocktail glass" -> mainController.getCocktailGlassDrinks()
            "champagne flute" -> mainController.getChampagneFluteDrinks()
            else ->  throw IllegalArgumentException("Value $query is incorrect")
        }

        callback.enqueue(object: Callback<DrinkList?>{
            override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {
                if(response.isSuccessful){
                    drinkList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
                val drinkList = DrinkList()
                this@Repository.drinkList.postValue(drinkList)
            }

        })

        return drinkList
    }

    fun getDrink(id: Long): MutableLiveData<DrinkList>{

            mainController.getDrink(id).enqueue(object : Callback<DrinkList?> {

                override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {

                    Log.d("TESTING__", "URL: " + response.raw().request().url())

                    if(response.isSuccessful){
                        randomDrink.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
                    t.printStackTrace()
                    val drinkList = DrinkList()
                    randomDrink.postValue(drinkList)
                }
            })

        return randomDrink
    }


}