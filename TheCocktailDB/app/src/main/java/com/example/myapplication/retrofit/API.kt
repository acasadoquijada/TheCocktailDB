package com.example.myapplication.retrofit

import com.example.myapplication.model.category.CategoryList
import com.example.myapplication.model.Drink
import com.example.myapplication.model.glass.GlassList
import retrofit2.Call
import retrofit2.http.GET

public interface API{

    @GET("random.php")
    fun getRandomCocktail(): Call<Drink>

    @GET("list.php?c=list")
    fun getCategories(): Call<CategoryList>

    @GET("list.php?g=list")
    fun getGlasses(): Call<GlassList>

}