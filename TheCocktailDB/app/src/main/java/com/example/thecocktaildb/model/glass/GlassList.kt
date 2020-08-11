package com.example.thecocktaildb.model.glass

import com.google.gson.annotations.SerializedName

public class GlassList{

    @SerializedName("drinks")
    var glassList: MutableList<Glass> = mutableListOf()
}