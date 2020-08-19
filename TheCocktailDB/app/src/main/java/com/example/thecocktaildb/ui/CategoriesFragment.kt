package com.example.thecocktaildb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.thecocktaildb.R
import com.example.thecocktaildb.databinding.FragmentCategoriesBinding
import com.example.thecocktaildb.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.category_card_view.view.*


class CategoriesFragment : Fragment() {

    private lateinit var mBinding : FragmentCategoriesBinding
    private lateinit var viewModel: HomeViewModel
    private var categoryInformation: MutableList<Pair<View,String>> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupDatabinding(inflater, container)
      //  setupCategories()
        setupCategoryInformation()
        setCategoryName()
        return getRootView()
    }

    private fun setupDatabinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories,container,false)
    }

    private fun getRootView():View{
        return mBinding.root
    }

    private fun navigateToDrinkListFragment(query: String){
        val action =
        CategoriesFragmentDirections.actionCategoriesFragmentToDrinkListFragment(query)

        NavHostFragment.findNavController(this).navigate(action)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }

    private fun setupCategoryInformation(){
        categoryInformation.add(Pair(mBinding.alcohol.root, getString(R.string.category_alcohol)))
        categoryInformation.add(Pair(mBinding.nonAlcohol.root, getString(R.string.category_no_alcohol)))
        categoryInformation.add(Pair(mBinding.ordinaryDrink.root, getString(R.string.category_ordinary_drink)))
        categoryInformation.add(Pair(mBinding.cocktail.root, getString(R.string.category_cocktail)))
        categoryInformation.add(Pair(mBinding.cocktailGlass.root, getString(R.string.category_cocktail_glass)))
        categoryInformation.add(Pair(mBinding.champagneFlute.root, getString(R.string.category_champagne_flute)))
    }

    private fun setCategoryName(){

        for (par: Pair<View,String> in categoryInformation){
            par.first.categoryName.text = par.second
            par.first.setOnClickListener{
                navigateToDrinkListFragment(par.second)
            }
        }
    }
}