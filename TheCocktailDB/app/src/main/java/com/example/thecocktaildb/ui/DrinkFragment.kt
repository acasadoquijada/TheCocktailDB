package com.example.thecocktaildb.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.R
import com.example.thecocktaildb.adapter.IngredientAdapter
import com.example.thecocktaildb.databinding.DrinkFragmentBinding
import com.example.thecocktaildb.model.Ingredient
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.ui.util.ViewModelAbstractFragment
import com.example.thecocktaildb.viewmodel.ViewModel
import com.squareup.picasso.Picasso

class DrinkFragment : ViewModelAbstractFragment() {

    private lateinit var viewModel: ViewModel
    private lateinit var mBinding: DrinkFragmentBinding
    private val defaultDrinkId = -1L
    private var drinkId: Long = defaultDrinkId

    private lateinit var adapter: IngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupDatabinding(inflater, container)
        mBinding.cocktailImage.clipToOutline = true // will be changed
        getDrinkId()
        setupRecyclerView()
        return getRootView()
    }

    override fun setupDatabinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.drink_fragment,container,false)
    }

    override fun getRootView(): View {
        return mBinding.root
    }

    override fun getViewModel() {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
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
        val manager = GridLayoutManager(context, 2)
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
        setupViewModel()
    }

    private fun setupViewModel(){
        getViewModel()
        observeDrink()
    }

    private fun observeDrink(){
        viewModel.getDrink(drinkId).observe(viewLifecycleOwner, Observer { drink ->
            setupDrinksAndUI(drink)
        })
    }

    private fun setupDrinksAndUI(drink: Drink){
        setupDrinksInAdapter(drink.getIngredients())
        setupUI(drink)
    }

    private fun setupUI(drink: Drink){
        setDrinkName(drink.name)
        setDrinkImage(drink.image)
        setDrinkTypeAndGlass(drink.category, drink.glass)
        setDrinkInstructions(drink.instruction)
    }

    private fun setDrinkName(name: String?){
        name?.let {
            mBinding.cocktailTitle.text = name
        }
    }

    private fun setDrinkImage(image: String?){
        image?.let{
            Picasso.get().load(image).into(mBinding.cocktailImage)
        }
    }

    private fun setDrinkTypeAndGlass(category: String?, glass: String?) {

        category?.let {
            glass?.let { it1 ->
                val typeAndGlass = "$it1 - $it"
                mBinding.typeAndGlass.text = typeAndGlass
            }
        }
    }

    private fun setDrinkInstructions(instructions: String?){
        instructions?.let {
            mBinding.instructions.text = instructions
        }
    }

    private fun setupDrinksInAdapter(ingredientList: List<Ingredient>){
        adapter.setIngredients(ingredientList)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if(isDrinkIdDefault()){
            inflater.inflate(R.menu.random_and_share_menu, menu);

        } else{
            inflater.inflate(R.menu.share_menu, menu);
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun isDrinkIdDefault(): Boolean{
        return drinkId == defaultDrinkId
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
        Toast.makeText(requireContext(),"This should share drink",Toast.LENGTH_SHORT).show()
        return true
    }
}