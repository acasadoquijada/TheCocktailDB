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
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.R
import com.example.thecocktaildb.adapter.DrinkAdapter
import com.example.thecocktaildb.databinding.DrinkListFragmentBinding
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.viewmodel.ViewModel

class DrinkListFragment : Fragment(), DrinkAdapter.ItemClickListener {

    lateinit var mBinding : DrinkListFragmentBinding
    lateinit var viewModel: ViewModel
    lateinit var adapter: DrinkAdapter
    lateinit var query: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupDatabinding(inflater, container)
        setupRecyclerView()
        return getRootView()
    }
    private fun setupRecyclerView(){
        setupLayoutManager()
        setupAdapter()
    }

    private fun setupLayoutManager(){
        getRecyclerView().layoutManager = createGridLayoutManager()

    }

    private fun createGridLayoutManager(): GridLayoutManager? {
        val manager = GridLayoutManager(context, 3)
        manager.orientation = RecyclerView.VERTICAL
        return manager
    }

    private fun setupAdapter(){
        createAdapter()
        setAdapter()
    }

    private fun createAdapter(){
        adapter = DrinkAdapter(this)
    }

    private fun setAdapter(){
        getRecyclerView().adapter = adapter
    }

    private fun getRecyclerView(): RecyclerView {
        return mBinding.drinkListRecyclerView
    }


    private fun setupDatabinding(inflater: LayoutInflater, container: ViewGroup?){
        mBinding = DataBindingUtil.inflate(inflater, R.layout.drink_list_fragment,container,false)
    }

    private fun getRootView(): View {
        return mBinding.root
    }


    private fun getQuery() {
        arguments?.let {
            query =  DrinkListFragmentArgs.fromBundle(requireArguments()).query
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getQuery()
        setupViewModel()
    }

    private fun setupViewModel(){
        getViewModel()
        observeDrinks()
    }

    private fun getViewModel(){
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
    }

    private fun observeDrinks(){
        viewModel.getDrinkList(query).observe(viewLifecycleOwner, Observer { drinkList ->
            setDrinks(drinkList)
        })
    }

    private fun setDrinks(drinkList: List<Drink>){
        adapter.setDrinks(drinkList)

    }

    private fun navigateToDrinkFragment(drinkId: Long){
        val action =
            DrinkListFragmentDirections.actionDrinkListFragmentToHomeFragment(drinkId)

        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onItemClick(clickedItem: Int) {
        navigateToDrinkFragment(viewModel.getDrinkId(clickedItem))
    }
}