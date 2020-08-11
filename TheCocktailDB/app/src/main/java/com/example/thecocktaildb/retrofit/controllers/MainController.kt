package com.example.thecocktaildb.retrofit.controllers

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

}