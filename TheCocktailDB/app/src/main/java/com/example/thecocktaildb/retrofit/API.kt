package com.example.thecocktaildb.retrofit

import com.example.thecocktaildb.model.category.CategoryList
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.model.glass.GlassList
import retrofit2.Call
import retrofit2.http.GET

public interface API{

    @GET("random.php")
    fun getRandomCocktail(): Call<DrinkList>

    @GET("list.php?c=list")
    fun getCategories(): Call<CategoryList>

    @GET("list.php?g=list")
    fun getGlasses(): Call<GlassList>

}