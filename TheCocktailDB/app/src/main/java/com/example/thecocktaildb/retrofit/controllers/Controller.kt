package com.example.thecocktaildb.retrofit.controllers

import android.util.Log
import com.example.thecocktaildb.model.category.CategoryList
import com.example.thecocktaildb.retrofit.API
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class Controller: Callback<CategoryList> {

    fun start() {

        val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        val gson = GsonBuilder().setLenient().create();

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

        val api = retrofit.create(API::class.java)

        val call:Call<CategoryList>  = api.getCategories()

        call.enqueue(this)

    }

    override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
        if(response.isSuccessful){
            Log.d("TESTING__", "YOU DID IT!")

            val categories: CategoryList? =  response.body()

            if (categories != null) {
                for(category in categories.categoryList){
                    Log.d("TESTING_", "name: $category")
                }
            }
        }
    }


    override fun onFailure(call: Call<CategoryList>, t: Throwable) {
        t.printStackTrace();
    }

}