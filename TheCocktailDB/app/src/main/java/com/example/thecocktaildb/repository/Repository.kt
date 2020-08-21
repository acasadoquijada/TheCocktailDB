package com.example.thecocktaildb.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.controllers.MainController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository() {

    private lateinit var randomDrink: MutableLiveData<Drink>

    private var drinkList: MutableLiveData<List<Drink>> = MutableLiveData()

    private val mainController: MainController = MainController()

    fun getDrinkPosition(position: Int): Long{

        drinkList.value?.get(position)?.let {
            return it.id
        }
        return -1
    }

    fun getRandomCocktail(new:Boolean): MutableLiveData<Drink> {

        if(new || randomDrink.value == null){
            mainController.getRandomDrinkCall().enqueue(object : Callback<DrinkList?> {

                override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {
                    if(response.isSuccessful){
                        randomDrink.postValue(response.body()?.list?.get(0))
                    }
                }

                override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
                    randomDrink.postValue(Drink())
                }
            })
        }

        return randomDrink
    }

    fun getDrinkList(query: String) : MutableLiveData<List<Drink>>{

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
                    drinkList.postValue(response.body()?.list)
                }
            }

            override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return drinkList
    }

    fun getDrink(id: Long): MutableLiveData<Drink>{

            mainController.getDrink(id).enqueue(object : Callback<DrinkList?> {

                override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {
                    if(response.isSuccessful){
                        randomDrink.postValue(response.body()?.list?.get(0))
                    }
                }

                override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        return randomDrink
    }
}