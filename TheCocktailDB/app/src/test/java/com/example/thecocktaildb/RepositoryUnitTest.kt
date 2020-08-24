package com.example.thecocktaildb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.repository.Repository
import getOrAwaitValue
import junit.framework.Assert
import junit.framework.Assert.assertEquals
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
/*
    @Test
    fun drinkIsNotifiedWithRandomCocktail(){
        val drink = Drink()
        repository.getDrink(1177).value = drink
        assertEquals(repository.getRandomCocktail(false).getOrAwaitValue(), drink)
    }*/


    @Test
    fun getDrinkIdRetrievesActualPosition() {

        // This should be changed. I should inject a Repository with the actual data I want

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

        // This should be changed. I should inject a Repository with the actual data I want

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

        for(string: String in list){
            repository.getDrinkList(string).value = drinkList
            assertEquals(repository.getDrinkList(string).getOrAwaitValue(), drinkList)
        }
    }
}