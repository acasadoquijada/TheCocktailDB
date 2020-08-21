package com.example.thecocktaildb.model.drink

import android.util.Log
import com.example.thecocktaildb.model.Ingredient
import com.google.gson.annotations.SerializedName

class Drink{

    @SerializedName("idDrink")
    var id: Long = 0

    @SerializedName("strDrink")
    var name: String? = ""

    @SerializedName("strCategory")
    var category: String? = ""

    @SerializedName("strAlcoholic")
    var alcoholic: String? = ""

    @SerializedName("strGlass")
    var glass: String? = ""

    @SerializedName("strInstructions")
    var instruction: String? = ""

    @SerializedName("strDrinkThumb")
    var image: String? = ""

    @SerializedName("strIngredient1")
    var ingredient1: String? = ""

    @SerializedName("strIngredient2")
    var ingredient2: String? = ""

    @SerializedName("strIngredient3")
    var ingredient3: String? = ""

    @SerializedName("strIngredient4")
    var ingredient4: String? = ""

    @SerializedName("strIngredient5")
    var ingredient5: String? = ""

    @SerializedName("strIngredient6")
    var ingredient6: String? = ""

    @SerializedName("strIngredient7")
    var ingredient7: String? = ""

    @SerializedName("strIngredient8")
    var ingredient8: String? = ""

    @SerializedName("strIngredient9")
    var ingredient9: String? = ""

    @SerializedName("strIngredient10")
    var ingredient10: String? = ""

    @SerializedName("strIngredient11")
    var ingredient11: String? = ""

    @SerializedName("strIngredient12")
    var ingredient12: String? = ""

    @SerializedName("strIngredient13")
    var ingredient13: String? = ""

    @SerializedName("strIngredient14")
    var ingredient14: String? = ""

    @SerializedName("strIngredient15")
    var ingredient15: String? = ""

    @SerializedName("strMeasure1")
    var measure1: String? = ""

    @SerializedName("strMeasure2")
    var measure2: String? = ""

    @SerializedName("strMeasure3")
    var measure3: String? = ""

    @SerializedName("strMeasure4")
    var measure4: String? = ""

    @SerializedName("strMeasure5")
    var measure5: String? = ""

    @SerializedName("strMeasure6")
    var measure6: String? = ""

    @SerializedName("strMeasure7")
    var measure7: String? = ""

    @SerializedName("strMeasure8")
    var measure8: String? = ""

    @SerializedName("strMeasure9")
    var measure9: String? = ""

    @SerializedName("strMeasure10")
    var measure10: String? = ""

    @SerializedName("strMeasure11")
    var measure11: String? = ""

    @SerializedName("strMeasure12")
    var measure12: String? = ""

    @SerializedName("strMeasure13")
    var measure13: String? = ""

    @SerializedName("strMeasure14")
    var measure14: String? = ""

    @SerializedName("strMeasure15")
    var measure15: String? = ""


    override fun toString(): String {
       // return "$id $name $category $alcoholic $instruction $image $ingredient1 $ingredient2 $measure1 $measure2"

        if(ingredient15 == null){
            return "THIS IS NULL"
        }
        return "$ingredient15"
    }

    fun getIngredients() : MutableList<Ingredient>{

        val ingredientList: MutableList<Ingredient> = ArrayList()

        ingredient1?.let { measure1?.let{it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient1?.let { measure1?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient2?.let { measure2?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient3?.let { measure3?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient4?.let { measure4?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient5?.let { measure5?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient6?.let { measure6?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient7?.let { measure7?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient8?.let { measure8?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient9?.let { measure9?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient10?.let { measure10?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient11?.let { measure11?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient12?.let { measure12?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient13?.let { measure13?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient14?.let { measure14?.let {it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        ingredient15?.let { measure15?.let { it1 ->
            ingredientList.add(Ingredient(it,it1)) } }

        return ingredientList
    }

    fun getParsedInstruction() : String? {
        return instruction?.replace(".",".\n\n")
    }
}