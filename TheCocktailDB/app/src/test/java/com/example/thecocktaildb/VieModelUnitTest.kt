package com.example.thecocktaildb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.viewmodel.ViewModel
import getOrAwaitValue
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VieModelUnitTest{

    private lateinit var viewModel: ViewModel

    @Rule @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = ViewModel()
    }

    @Test
    fun drinkIsNotifiedWithRandomCocktail(){
        val drink = Drink()
        viewModel.getDrink().value = drink
        assertEquals(viewModel.getDrink().getOrAwaitValue(), drink)
    }

    @Test
    fun drinkIsNotifiedWithSpecificCocktail(){
        val drink = Drink()
        viewModel.getDrink(drinkId = 11007).value = drink

        assertEquals(viewModel.getDrink(drinkId = 11007).getOrAwaitValue(), drink)
    }

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

        viewModel.getDrinkList("alcohol").value = drinkList

        assertEquals(3, viewModel.getDrinkId(1))
    }

    @Test
    fun getDrinkRetrievesUnrealPosition() {

        // This should be changed. I should inject a Repository with the actual data I want

        assertEquals(-1, viewModel.getDrinkId(333))
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
            viewModel.getDrinkList(string).value = drinkList
            assertEquals(viewModel.getDrinkList(string).getOrAwaitValue(), drinkList)
        }
    }
}