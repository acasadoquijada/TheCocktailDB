package com.example.myapplication.model.category

import com.example.myapplication.model.category.Category
import com.google.gson.annotations.SerializedName

public class CategoryList{

    @SerializedName("drinks")
    var categoryList: MutableList<Category> = mutableListOf()
}