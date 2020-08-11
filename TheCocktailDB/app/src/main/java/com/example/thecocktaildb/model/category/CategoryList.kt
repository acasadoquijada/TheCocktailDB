package com.example.thecocktaildb.model.category

import com.google.gson.annotations.SerializedName

class CategoryList{

    @SerializedName("drinks")
    var categoryList: MutableList<Category> = mutableListOf()
}