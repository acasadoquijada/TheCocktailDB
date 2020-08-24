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
import com.example.thecocktaildb.databinding.DrinkFragmentBinding
import com.example.thecocktaildb.viewmodel.ViewModel
import com.squareup.picasso.Picasso

class DrinkFragment : Fragment() {

    companion object {
        fun newInstance() = DrinkFragment()
    }

    private lateinit var viewModel: ViewModel
    private lateinit var mBinding: DrinkFragmentBinding
    private var drinkId: Long = -1L

    private lateinit var adapter: IngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.drink_fragment,container,false)
        getDrinkId()
        setupRecyclerView()
        return mBinding.root
    }


    private fun getDrinkId() {
        arguments?.let {
            drinkId =  DrinkFragmentArgs.fromBundle(requireArguments()).drinkId
        }
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
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.getDrink(drinkId = drinkId).observe(viewLifecycleOwner, Observer { drink ->

                mBinding.cocktailTitle.text = drink.name

                Picasso.get().load(drink.image).into(mBinding.cocktailImage)

                adapter.setIngredients(drink.getIngredients())

                mBinding.typeAndGlass.text = drink.category + " - " +  drink.glass

                mBinding.instructions.text = drink.getParsedInstruction()

        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if(drinkId == -1L){
            inflater.inflate(R.menu.random_and_share_menu, menu);

        } else{
            inflater.inflate(R.menu.share_menu, menu);
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_random -> getRandomCocktail()
            R.id.action_share -> shareCocktail()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getRandomCocktail(): Boolean{
        viewModel.updateRandomDrink()
        return true
    }

    private fun shareCocktail(): Boolean{
        Toast.makeText(requireContext(),"TESTING",Toast.LENGTH_SHORT).show()
        return true
    }
}