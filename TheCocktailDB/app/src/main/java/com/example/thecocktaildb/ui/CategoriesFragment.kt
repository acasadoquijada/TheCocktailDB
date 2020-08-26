package com.example.thecocktaildb.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.R
import com.example.thecocktaildb.adapter.CategoriesAdapter
import com.example.thecocktaildb.adapter.DrinkAdapter
import com.example.thecocktaildb.databinding.FragmentCategoriesBinding
import com.example.thecocktaildb.ui.util.DataBindingAbstractFragment
import com.example.thecocktaildb.ui.util.OnClickElementInterface
import com.example.thecocktaildb.viewmodel.ViewModel
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration
import kotlinx.android.synthetic.main.category_card_view.view.*


class CategoriesFragment : DataBindingAbstractFragment(), OnClickElementInterface {

    private lateinit var mBinding : FragmentCategoriesBinding
    private lateinit var viewModel: ViewModel
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var drinkAdapter: DrinkAdapter
    private var categoryInformation: MutableList<Pair<View,String>> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupDatabinding(inflater, container)
        setupCategories()
        setupRecyclerView()

        drinkAdapter = DrinkAdapter(this)
        return getRootView()
    }

    override fun onItemClick(clickedItem: Int) {

        if(getRecyclerView().adapter == categoryAdapter){
            Log.d("TESTING__", "category name: ${viewModel.getCategoryName(clickedItem)}")
            navigateToDrinkListFragment(viewModel.getCategoryName(clickedItem))
        } else{
            navigateToDrinkFragment(viewModel.getDrinkId(clickedItem))
        }
    }

    private fun navigateToDrinkFragment(drinkId: Long){
        val action =
            CategoriesFragmentDirections.actionCategoriesFragmentToHomeFragment(drinkId)

        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun setupRecyclerView(){
        setupLayoutManager()
        setupAdapter()
    }

    private fun setupLayoutManager(){
        getRecyclerView().layoutManager = createGridLayoutManager()
        getRecyclerView().addItemDecoration(LayoutMarginDecoration(1, 16))

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
        categoryAdapter = CategoriesAdapter(this)
    }

    private fun setAdapter(){
        getRecyclerView().adapter = categoryAdapter
    }

    private fun setCategoryInformationAdapter(){
        categoryAdapter.updateCategories(viewModel.getCategories())
    }

    private fun getRecyclerView(): RecyclerView {
        return mBinding.recyclerView
    }


    private fun getViewModel() {
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
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
        getViewModel()
        setCategoryInformationAdapter()
       // setupCategories()
    }


    private fun setupCategories(){
        setCategoryInformation()
        setCategoryLogic()
    }

    private fun setCategoryInformation(){
       /* categoryInformation.add(Pair(mBinding.alcohol.root, getString(R.string.category_alcohol)))
        categoryInformation.add(Pair(mBinding.nonAlcohol.root, getString(R.string.category_no_alcohol)))
        categoryInformation.add(Pair(mBinding.ordinaryDrink.root, getString(R.string.category_ordinary_drink)))
        categoryInformation.add(Pair(mBinding.cocktail.root, getString(R.string.category_cocktail)))
        categoryInformation.add(Pair(mBinding.cocktailGlass.root, getString(R.string.category_cocktail_glass)))
        categoryInformation.add(Pair(mBinding.champagneFlute.root, getString(R.string.category_champagne_flute)))*/
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
              //  navigateToDrinkListFragment(query)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {

                if(s.isEmpty()){
                    getRecyclerView().adapter = categoryAdapter
                    val layout = getRecyclerView().layoutManager as GridLayoutManager
                    layout.spanCount = 1
                } else{
                    viewModel.getDrinkList(s).observe(viewLifecycleOwner, Observer {
                        drinkAdapter.setDrinks(it)

                        // There should be a check here. Dont wanna to re-attach adapters
                        getRecyclerView().adapter = drinkAdapter
                        val layout = getRecyclerView().layoutManager as GridLayoutManager
                        layout.spanCount = 3
                    })
                }
                return false
            }
        })

/*
        searchView?.setOnSearchClickListener {
            mBinding.categoriesGridLayout.visibility = View.INVISIBLE
        }*/

        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}