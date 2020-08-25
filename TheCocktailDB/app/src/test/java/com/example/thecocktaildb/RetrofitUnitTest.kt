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


    private fun assertDrinkInCategoryIsNotEmpty(drinkList: DrinkList?, size: Int? = drinkList?.list?.size){

        size?.let {
            for (i in 0 until size) {
                assert(drinkList?.list?.get(i)?.id != 0L)
                assert(!drinkList?.list?.get(i)?.name.equals(""))
                assert(!drinkList?.list?.get(i)?.image.equals(""))
            }
        }
    }

    private fun assertDrinkIsNotEmpty(drinkList: DrinkList?){
        assert(drinkList?.list?.get(0)?.id != 0L)
        assert(!drinkList?.list?.get(0)?.name.equals(""))
        assert(!drinkList?.list?.get(0)?.instruction.equals(""))
        assert(!drinkList?.list?.get(0)?.glass.equals(""))
        assert(!drinkList?.list?.get(0)?.category.equals(""))
        assert(!drinkList?.list?.get(0)?.image.equals(""))
        drinkList?.list?.get(0)?.getIngredients()?.isNotEmpty()?.let { assert(it) }

    }

    // We ensure that the call is correct (url exists and we retrieve a JSON object)
    private fun assertResponseIsTrue(response: Response<DrinkList>){
        assert(response.isSuccessful)
    }

    // In this case we know the name of the drink beforehand
    private fun assertNameIsCorrect(drinkList: DrinkList?){
        assert(drinkList?.list?.get(0)?.name.equals("Margarita"))
        assert(drinkList?.list?.get(0)?.id != 0L)
        assert(!drinkList?.list?.get(0)?.instruction.equals(""))
        assert(!drinkList?.list?.get(0)?.glass.equals(""))
        assert(!drinkList?.list?.get(0)?.category.equals(""))
        assert(!drinkList?.list?.get(0)?.image.equals(""))
        drinkList?.list?.get(0)?.getIngredients()?.isNotEmpty()?.let { assert(it) }
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

            assertDrinkIsNotEmpty(drinkList)

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

            assertDrinkInCategoryIsNotEmpty(drinkList)

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

            assertDrinkInCategoryIsNotEmpty(drinkList)

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

            assertDrinkInCategoryIsNotEmpty(drinkList)

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

            assertDrinkInCategoryIsNotEmpty(drinkList)

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

            assertDrinkInCategoryIsNotEmpty(drinkList)

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

            assertDrinkInCategoryIsNotEmpty(drinkList)

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

    @Test
    fun searchDrinkByName(){
        try{

            val name = "margarita"

            val response: Response<DrinkList> = mainController.searchDrinkByName(name).execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }

}
