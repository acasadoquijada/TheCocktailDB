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

    @GET("filter.php?a=Alcoholic")
    fun getAlcoholicDrinks(): Call<DrinkList>

    @GET("filter.php?a=Non_Alcoholic")
    fun getNonAlcoholicDrinks(): Call<DrinkList>

    @GET("filter.php?c=Ordinary_Drink")
    fun getOrdinaryDrinks(): Call<DrinkList>

    @GET("filter.php?c=Cocktail")
    fun getCocktailsDrinks(): Call<DrinkList>

    @GET("filter.php?g=Cocktail_glass")
    fun getCocktailGlassDrinks(): Call<DrinkList>

    @GET("filter.php?g=Champagne_flute")
    fun getChampagneFluteDrinks(): Call<DrinkList>
}