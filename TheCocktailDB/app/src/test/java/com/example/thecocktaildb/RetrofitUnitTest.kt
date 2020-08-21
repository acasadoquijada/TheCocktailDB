package com.example.thecocktaildb

import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.controllers.MainController
import junit.framework.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import java.io.IOException


@RunWith(JUnit4::class)
class RetrofitUnitTest{

    lateinit var mainController:MainController

    @Before
    fun setup(){
        mainController = MainController()
    }

    private fun assertDrinkIsNotEmpty(drinkList: DrinkList?, size: Int? = drinkList?.drinkList?.size){

        size?.let {
            for(i in 0 until size){
                assert(!drinkList?.drinkList?.get(i)?.name.equals(""))
            }
        }
    }


    private fun assertResponseIsTrue(response: Response<DrinkList>){
        assert(response.isSuccessful)
    }

    private fun assertNameIsCorrect(drinkList: DrinkList?){
        assert(drinkList?.drinkList?.get(0)?.name.equals("Margarita"))
    }

    private fun failTest(e: IOException){
        e.printStackTrace()
        fail()
    }

    @Test
    fun getRandomDrink(){
        try {

            val response: Response<DrinkList> = mainController.getRandomDrinkCall().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList,1) // The response of getRandomDrinkCall returns a list with only one drink

        } catch (e:IOException) {
            failTest(e)
        }
    }

    @Test
    fun getAlcoholicDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getAlcoholicDrinksCall().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

    @Test
    fun getNonAlcoholicDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getNonAlcoholicDrinks().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

    @Test
    fun getCocktailsDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getCocktailsDrinks().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

    @Test
    fun getOrdinaryDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getOrdinaryDrinks().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

    @Test
    fun getCocktailGlassDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getCocktailGlassDrinks().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

    @Test
    fun getChampagneFluteDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getChampagneFluteDrinks().execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }



    @Test
    fun getDrinkById(){

        try{

            val id: Long = 11007

            val response: Response<DrinkList> = mainController.getDrink(id).execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertNameIsCorrect(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

}
