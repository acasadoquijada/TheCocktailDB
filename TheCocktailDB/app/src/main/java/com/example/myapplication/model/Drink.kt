package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

public class Drink{

    //idDrink
    @SerializedName("idDrink")
    private var id: Long = 0
    //strDrink
    @SerializedName("strDrink")
    private  var name: String = ""
    //strCategory
    @SerializedName("strCategory")
    private  var category: String = ""
    //strAlcoholic
    @SerializedName("strAlcoholic")
    private  var alcoholic: String = ""
    //strInstructions
    @SerializedName("strInstructions")
    private  var instruction: String = ""

    override fun toString(): String {
        return "$id $name $category $alcoholic$instruction"
    }
}