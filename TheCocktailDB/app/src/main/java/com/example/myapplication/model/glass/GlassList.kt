package com.example.myapplication.model.glass

import com.example.myapplication.model.category.Category
import com.google.gson.annotations.SerializedName

public class GlassList{

    @SerializedName("drinks")
    var glassList: MutableList<Glass> = mutableListOf()
}