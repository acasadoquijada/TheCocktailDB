package com.example.thecocktaildb.retrofit.controllers

import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.API
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainController {

    private val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private lateinit var retrofit:Retrofit
    private lateinit var api:API

    init{
        createRetrofit(createGson())
        createAPI()
    }

    private fun createRetrofit(gson: Gson){
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    private fun createGson(): Gson{
        return GsonBuilder().setLenient().create()
    }

    private fun createAPI() {
        api = retrofit.create(API::class.java)
    }

    fun getRandomDrinkCall(): Call<DrinkList>{
        return api.getRandomCocktail()
    }

    fun getAlcoholicDrinksCall():Call<DrinkList>{
        return api.getAlcoholicDrinks()
    }

    fun getNonAlcoholicDrinks(): Call<DrinkList>{
        return api.getNonAlcoholicDrinks()
    }

    fun getCocktailsDrinks(): Call<DrinkList>{
        return api.getCocktailsDrinks()
    }

    fun getOrdinaryDrinks(): Call<DrinkList>{
        return api.getOrdinaryDrinks()
    }

    fun getCocktailGlassDrinks(): Call<DrinkList>{
        return api.getCocktailGlassDrinks()
    }

    fun getChampagneFluteDrinks(): Call<DrinkList>{
        return api.getChampagneFluteDrinks()
    }

    fun getDrink(id: Long): Call<DrinkList>{
        return api.getDrink(id)
    }

    fun searchDrinkByName(name: String): Call<DrinkList>{
        return api.searchDrinkByName(name)
    }

    fun searchDrinkByIngredient(ingredient: String): Call<DrinkList>{
        return api.searchDrinkByIngredient(ingredient)
    }

}