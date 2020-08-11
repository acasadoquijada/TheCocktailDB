package com.example.thecocktaildb.model.drink

import com.google.gson.annotations.SerializedName

class Drink{

    @SerializedName("idDrink")
    private var id: Long = 0

    @SerializedName("strDrink")
    private  var name: String = ""

    @SerializedName("strCategory")
    private  var category: String = ""

    @SerializedName("strAlcoholic")
    private  var alcoholic: String = ""

    @SerializedName("strInstructions")
    private  var instruction: String = ""

    @SerializedName("strDrinkThumb")
    private  var image: String = ""

    @SerializedName("strIngredient1")
    private var ingredient1: String = ""

    @SerializedName("strIngredient2")
    private var ingredient2: String = ""

    @SerializedName("strIngredient3")
    private var ingredient3: String = ""

    @SerializedName("strIngredient4")
    private var ingredient4: String = ""

    @SerializedName("strIngredient5")
    private var ingredient5: String = ""

    @SerializedName("strIngredient6")
    private var ingredient6: String = ""

    @SerializedName("strIngredient7")
    private var ingredient7: String = ""

    @SerializedName("strIngredient8")
    private var ingredient8: String = ""

    @SerializedName("strIngredient9")
    private var ingredient9: String = ""

    @SerializedName("strIngredient10")
    private var ingredient10: String = ""

    @SerializedName("strIngredient11")
    private var ingredient11: String = ""

    @SerializedName("strIngredient12")
    private var ingredient12: String = ""

    @SerializedName("strIngredient13")
    private var ingredient13: String = ""

    @SerializedName("strIngredient14")
    private var ingredient14: String = ""

    @SerializedName("strIngredient15")
    private var ingredient15: String = ""

    @SerializedName("strMeasure1")
    private var measure1: String = ""

    @SerializedName("strMeasure2")
    private var measure2: String = ""

    @SerializedName("strMeasure3")
    private var measure3: String = ""

    @SerializedName("strMeasure4")
    private var measure4: String = ""

    @SerializedName("strMeasure5")
    private var measure5: String = ""

    @SerializedName("strMeasure6")
    private var measure6: String = ""

    @SerializedName("strMeasure7")
    private var measure7: String = ""

    @SerializedName("strMeasure8")
    private var measure8: String = ""

    @SerializedName("strMeasure9")
    private var measure9: String = ""

    @SerializedName("strMeasure10")
    private var measure10: String = ""

    @SerializedName("strMeasure11")
    private var measure11: String = ""

    @SerializedName("strMeasure12")
    private var measure12: String = ""

    @SerializedName("strMeasure13")
    private var measure13: String = ""

    @SerializedName("strMeasure14")
    private var measure14: String = ""

    @SerializedName("strMeasure15")
    private var measure15: String = ""


    override fun toString(): String {
        return "$id $name $category $alcoholic $instruction $image $ingredient1 $ingredient2 $measure1 $measure2"
    }
}