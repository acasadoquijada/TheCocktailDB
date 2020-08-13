package com.example.thecocktaildb.model

class Ingredient(name: String?, measure: String?) {

    var name: String? = ""
    var measure: String? = ""

    init {
        this.name = name
        this.measure = measure
    }
}