package com.example.thecocktaildb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.repository.Repository
import getOrAwaitValue
import junit.framework.Assert
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RepositoryUnitTest{
    private lateinit var repository: Repository

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = Repository()
    }

    @Test
    fun updateRandomDrink(){
        repository.updateRandomDrink()
    }

    @Test
    fun getDrinkIdRetrievesActualPosition() {


        val drinkList: MutableList<Drink>? = ArrayList()

        val drink1 = Drink()
        val drink2 = Drink()
        drink1.id = 1
        drink2.id = 3

        drinkList?.add(drink1)
        drinkList?.add(drink2)

        repository.getDrinkList("alcohol").value = drinkList

        assertEquals(3, repository.getDrinkId(1))
    }

    @Test
    fun getDrinkRetrievesUnrealPosition() {

        repository.getDrinkList("alcohol")

        assertEquals(-1, repository.getDrinkId(999999))
    }


    @Test
    fun drinkListIsNotifiedCorrectly(){

        val drinkList: List<Drink>? = ArrayList()
        val list: MutableList<String> = ArrayList()

        list.add("alcohol")
        list.add("no alcohol")
        list.add("ordinary drink")
        list.add("cocktail")
        list.add("cocktail glass")
        list.add("champagne flute")
        list.add("margarita")

        for(string: String in list){
            repository.getDrinkList(string).value = drinkList
            assertEquals(repository.getDrinkList(string).getOrAwaitValue(), drinkList)
        }
    }

    // getDrink

    @Test
    fun getDrinkByIdIsNotifiedCorrectly(){

        val drink = Drink()

        repository.getDrink(10).value = drink

        assertEquals(repository.getDrink(10).getOrAwaitValue(),drink)
    }

    @Test
    fun getDrinkRandomIsNotifiedCorrectly(){

        val drink = Drink()

        repository.getDrink(-1L).value = drink

        assertEquals(repository.getDrink(-1L).getOrAwaitValue(),drink)
    }

/*
    @Test
    fun searchDrinkByIngredient(){
        try{

            val ingredient = "Gin"

            val response: Response<DrinkList> = mainController.searchDrinkByIngredient(ingredient).execute()

            assertResponseIsTrue(response)

            val drinkList = response.body()

            assertDrinkInCategoryIsNotEmpty(drinkList)

        } catch (e:IOException) {
            failTest(e)
        }
    }
 */
}