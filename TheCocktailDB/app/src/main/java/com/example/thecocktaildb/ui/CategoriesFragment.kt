package com.example.thecocktaildb.ui

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.R
import com.example.thecocktaildb.adapter.DrinkAdapter
import com.example.thecocktaildb.databinding.FragmentCategoriesBinding
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.ui.util.DataBindingAbstractFragment
import com.example.thecocktaildb.viewmodel.ViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_card_view.view.*


class CategoriesFragment : DataBindingAbstractFragment() {

    private lateinit var mBinding : FragmentCategoriesBinding
    private var categoryInformation: MutableList<Pair<View,String>> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupDatabinding(inflater, container)
        setupCategories()
        return getRootView()
    }

    override fun setupDatabinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories,container,false)
    }

    override fun getRootView():View{
        return mBinding.root
    }

    private fun navigateToDrinkListFragment(query: String){
        val action =
        CategoriesFragmentDirections.actionCategoriesFragmentToDrinkListFragment(query)

        NavHostFragment.findNavController(this).navigate(action)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCategories()
    }


    private fun setupCategories(){
        setCategoryInformation()
        setCategoryLogic()
    }

    private fun setCategoryInformation(){
        categoryInformation.add(Pair(mBinding.alcohol.root, getString(R.string.category_alcohol)))
        categoryInformation.add(Pair(mBinding.nonAlcohol.root, getString(R.string.category_no_alcohol)))
        categoryInformation.add(Pair(mBinding.ordinaryDrink.root, getString(R.string.category_ordinary_drink)))
        categoryInformation.add(Pair(mBinding.cocktail.root, getString(R.string.category_cocktail)))
        categoryInformation.add(Pair(mBinding.cocktailGlass.root, getString(R.string.category_cocktail_glass)))
        categoryInformation.add(Pair(mBinding.champagneFlute.root, getString(R.string.category_champagne_flute)))
    }

    private fun setCategoryLogic(){

        for (par: Pair<View,String> in categoryInformation){
            setCategoryName(par.first, par.second)
            setOnClickListener(par.first, par.second)
        }
    }


    private fun setCategoryName(view: View, name: String){
        view.categoryName.text = name
    }

    private fun setOnClickListener(view: View, name: String){
        view.setOnClickListener{
            navigateToDrinkListFragment(name)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu);

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }

                searchView.clearFocus();

                searchItem.collapseActionView()

                navigateToDrinkListFragment(query)


                return false
            }

            override fun onQueryTextChange(s: String): Boolean {

                return false
            }

        })


        searchView?.setOnSearchClickListener {
            mBinding.categoriesGridLayout.visibility = View.INVISIBLE
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}