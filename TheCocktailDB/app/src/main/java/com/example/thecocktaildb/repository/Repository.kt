package com.example.thecocktaildb.repository

import androidx.lifecycle.MutableLiveData
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.controllers.MainController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository() {

    private var drink: MutableLiveData<Drink> = MutableLiveData()

    private var drinkList: MutableLiveData<List<Drink>> = MutableLiveData()

    private val mainController: MainController = MainController()

    private val updateDrinkListCallback =  object: Callback<DrinkList?>{
        override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {
            if(response.isSuccessful){
                updateDrinkList(response.body()?.list)
            }
        }

        override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
            t.printStackTrace()
        }
    }

    private val updateDrinkCallBack =  object: Callback<DrinkList?>{
        override fun onResponse(call: Call<DrinkList?>, response: Response<DrinkList?>) {
            if(response.isSuccessful){
                updateDrink(response.body()?.list)
            }
        }

        override fun onFailure(call: Call<DrinkList?>, t: Throwable) {
            t.printStackTrace()
        }
    }

    fun getDrinkId(position: Int): Long{

        var id: Long = -1

        if(drinkList.value?.get(position) != null){
            drinkList.value?.get(position)?.let {
                id = it.id
            }
        }

        return id
    }


    fun getDrinkList(query: String) : MutableLiveData<List<Drink>>{


        val callback: Call<DrinkList> = when(query){
            "alcohol" -> mainController.getAlcoholicDrinksCall()
            "no alcohol" -> mainController.getNonAlcoholicDrinks()
            "ordinary drink" -> mainController.getOrdinaryDrinks()
            "cocktail" -> mainController.getCocktailsDrinks()
            "cocktail glass" -> mainController.getCocktailGlassDrinks()
            "champagne flute" -> mainController.getChampagneFluteDrinks()
            else -> return searchDrink(query)
        }

        callback.enqueue(updateDrinkListCallback)

        return drinkList
    }

    fun getDrink(id: Long): MutableLiveData<Drink>{

        return if(id == -1L){
            getRandomDrink()
        } else{
            getDrinkById(id)
        }
    }


    private fun getRandomDrink(): MutableLiveData<Drink> {

        if(drink.value == null){
            updateRandomDrink()
        }

        return drink
    }

    private fun getDrinkById(id: Long): MutableLiveData<Drink>{
        mainController.getDrink(id).enqueue(updateDrinkCallBack)

        return drink
    }

    fun updateRandomDrink(){
        mainController.getRandomDrinkCall().enqueue(updateDrinkCallBack)
    }

    private fun searchDrink(name: String): MutableLiveData<List<Drink>>{
        mainController.searchDrinkByName(name).enqueue(updateDrinkListCallback)
        return drinkList
    }

    private fun updateDrinkList(drinkList: MutableList<Drink>?){

        if(!drinkList.isNullOrEmpty()){
            this.drinkList.postValue(drinkList)
        } else{
            this.drinkList.postValue(ArrayList())
        }
    }

    private fun updateDrink(drinkList: MutableList<Drink>?){
        if(drinkList?.isNullOrEmpty() == false){
            this.drink.postValue(drinkList[0])
        } else{
            this.drink.postValue(Drink())
        }
    }

}