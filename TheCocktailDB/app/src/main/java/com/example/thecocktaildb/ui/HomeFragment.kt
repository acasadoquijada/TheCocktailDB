package com.example.thecocktaildb.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.thecocktaildb.R
import com.example.thecocktaildb.databinding.HomeFragmentBinding
import com.example.thecocktaildb.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var mBinding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,container,false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getRandomCocktail().observe(viewLifecycleOwner, Observer { drinkList ->
            for (drink in drinkList.drinkList) {
                Log.d("TESTING",drink.toString())

                var value = drink.getIngredients();

                mBinding.cocktailTitle.text = drink.name
                Picasso.get().load(drink.image).into(mBinding.cocktailImage)
                mBinding.category.text = drink.category
                mBinding.glassType.text = drink.glass
                mBinding.instructions.text = drink.instruction
            }
        })
    }
}