package com.example.thecocktaildb.retrofit.controllers

import android.util.Log
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.API
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DrinkController: Callback<DrinkList> {

    fun start() {

        val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        val gson = GsonBuilder().setLenient().create();

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

        val api = retrofit.create(API::class.java)

        val call: Call<DrinkList> = api.getRandomCocktail()

        call.enqueue(this)

    }

    fun getRandomDrinkCall(): Call<DrinkList>{
        val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        val gson = GsonBuilder().setLenient().create();

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

        val api = retrofit.create(API::class.java)

        return api.getRandomCocktail()
    }

    override fun onResponse(call: Call<DrinkList>, response: Response<DrinkList>) {
        if(response.isSuccessful){
            Log.d("TESTING__", "YOU DID IT!")

            val drinkList: DrinkList? =  response.body()

            if (drinkList != null) {
                for(drink in drinkList.drinkList){
                    Log.d("TESTING_", "name: $drink")
                }
            }
        }
    }


    override fun onFailure(call: Call<DrinkList>, t: Throwable) {
        t.printStackTrace();
    }

}