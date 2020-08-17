package com.example.thecocktaildb

import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.model.drink.DrinkList
import com.example.thecocktaildb.retrofit.controllers.MainController
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


@RunWith(JUnit4::class)
class RetrofitUnitTest{

    lateinit var mainController:MainController

    @Before
    fun setup(){
        mainController = MainController()
    }


    @Test
    fun getRandomDrink(){
        try {

            val response: Response<DrinkList> = mainController.getRandomDrinkCall().execute()

            val drinkList = response.body()

            if (drinkList != null) {
                assert(!drinkList.drinkList[0].name.equals(""))
            }

        } catch (e:IOException) {
                e.printStackTrace();
        }
    }

    @Test
    fun getAlcoholicDrinks(){
        try {

            val response: Response<DrinkList> = mainController.getAlcoholicDrinksCall().execute()

            val drinkList = response.body()

            if (drinkList != null) {
                for(drink: Drink in drinkList.drinkList){
                    assert(!drink.name.equals(""))
                }
            }

        } catch (e:IOException) {
            e.printStackTrace();
        }
    }


}







/*
public class LoginAPITest {

    @Test
    public void login_Success() {

        APIEndpoints apiEndpoints = RetrofitHelper.getTesterInstance().create(APIEndpoints.class);

        Call<AuthResponse> call = apiEndpoints.postLogin();

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<AuthResponse> response = call.execute();
            AuthResponse authResponse = response.body();

            assertTrue(response.isSuccessful() && authResponse.getBearer().startsWith("TestBearer"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}*/