package com.example.myapplication.retrofit.controllers

import android.util.Log
import com.example.myapplication.model.category.CategoryList
import com.example.myapplication.model.glass.GlassList
import com.example.myapplication.retrofit.API
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class GlassController: Callback<GlassList> {

    fun start(){
        val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

        val gson = GsonBuilder().setLenient().create();

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

        val api = retrofit.create(API::class.java)

        val call:Call<GlassList>  = api.getGlasses()

        call.enqueue(this)
    }



    override fun onResponse(call: Call<GlassList>, response: Response<GlassList>) {

        if(response.isSuccessful){
            Log.d("TESTING_", "YOU DID IT")
            val glassList: GlassList? =  response.body()

            if (glassList != null) {
                for(glass in glassList.glassList){
                    Log.d("TESTING_", "name: $glass")
                }
            }
        }
        }

    override fun onFailure(call: Call<GlassList>, t: Throwable) {
        t.printStackTrace()
    }

}