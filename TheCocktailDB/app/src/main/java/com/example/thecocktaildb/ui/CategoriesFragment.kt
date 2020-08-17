package com.example.thecocktaildb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.thecocktaildb.R
import com.example.thecocktaildb.databinding.FragmentCategoriesBinding
import com.example.thecocktaildb.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_categories.view.*


class CategoriesFragment : Fragment() {

    lateinit var mBinding : FragmentCategoriesBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupDatabinding(inflater, container)
        setupCategories()
        return getRootView()
    }

    private fun setupDatabinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories,container,false)
    }

    private fun getRootView():View{
        return mBinding.root
    }

    private fun setupCategories(){
        mBinding.alcohol.categoryName.text = "Alcohol"
        mBinding.nonAlcohol.categoryName.text = "No alcohol"
        mBinding.ordinaryDrink.categoryName.text = "Ordinary drink"
        mBinding.cocktail.categoryName.text = "Cocktail"
        mBinding.cocktailGlass.categoryName.text = "CocktailGlass"
        mBinding.champagneFlute.categoryName.text = "Champagne flute"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getAlcoholicDrinks().observe(viewLifecycleOwner, Observer { drinkList ->
            for (drink in drinkList.drinkList) {
                Toast.makeText(requireContext(),"" + drink.id,Toast.LENGTH_SHORT).show()
            }
        })
    }


}