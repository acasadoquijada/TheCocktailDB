package com.example.thecocktaildb.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.R
import com.example.thecocktaildb.adapter.IngredientAdapter
import com.example.thecocktaildb.databinding.HomeFragmentBinding
import com.example.thecocktaildb.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var mBinding: HomeFragmentBinding

    private lateinit var adapter: IngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,container,false)

        setupRecyclerView()
        return mBinding.root
    }

    private fun setupRecyclerView(){
        setupLayoutManager()
        setupAdapter()
    }

    private fun setupLayoutManager(){
        getRecyclerView().layoutManager = createGridLayoutManager()

    }

    private fun createGridLayoutManager(): GridLayoutManager? {
        val manager = GridLayoutManager(context, 1)
        manager.orientation = RecyclerView.VERTICAL
        return manager
    }

    private fun setupAdapter(){
        createAdapter()
        setAdapter()
    }

    private fun createAdapter(){
        adapter = IngredientAdapter()
    }

    private fun setAdapter(){
        getRecyclerView().adapter = adapter
    }

    private fun getRecyclerView(): RecyclerView {
        return mBinding.ingredientRecyclerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getRandomCocktail().observe(viewLifecycleOwner, Observer { drinkList ->
            for (drink in drinkList.drinkList) {

                mBinding.cocktailTitle.text = drink.name

                Picasso.get().load(drink.image).into(mBinding.cocktailImage)

                Log.d("TESTING", "ingredient size in FRAGMENT ${drink.getIngredients().size}")
                adapter.ingredientList = drink.getIngredients()

                mBinding.typeAndGlass.text = drink.category + " - " +  drink.glass

                mBinding.instructions.text = drink.getParsedInstruction()

            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.random_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemClicked = item.itemId

        if(itemClicked == R.id.action_random){
            viewModel.getRandomCocktail(newCocktail = true)
            return true;
        }

        return super.onOptionsItemSelected(item)
    }
}