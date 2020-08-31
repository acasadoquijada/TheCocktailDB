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
import com.example.thecocktaildb.model.drink.Drink
import com.example.thecocktaildb.ui.util.DataBindingAbstractFragment
import com.example.thecocktaildb.ui.util.OnClickElementInterface
import com.example.thecocktaildb.viewmodel.ViewModel
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration


class CategoriesFragment : DataBindingAbstractFragment(), OnClickElementInterface {

    private lateinit var mBinding : FragmentCategoriesBinding
    private lateinit var viewModel: ViewModel
    private lateinit var categoryAdapter: CategoriesAdapter
    private lateinit var drinkAdapter: DrinkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupDatabinding(inflater, container)
        setupRecyclerView()

        return getRootView()
    }


    override fun onItemClick(clickedItem: Int) {
        if(getRecyclerView().adapter == categoryAdapter){
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
        setupAdapters()
        addItemDecorationRecyclerView()
    }

    private fun setupLayoutManager(){
        createAndSetGridLayoutManager()
    }

    private fun createAndSetGridLayoutManager() {
        getRecyclerView().layoutManager = createGridLayoutManager()
        addItemDecorationRecyclerView()
    }

    private fun addItemDecorationRecyclerView(){
        getRecyclerView().addItemDecoration(LayoutMarginDecoration(1, 16))
    }

    private fun createGridLayoutManager() : GridLayoutManager?{
        val manager = GridLayoutManager(context, 1)
        manager.orientation = RecyclerView.VERTICAL
        return manager
    }

    private fun setupAdapters(){
        createAdapters()
        setCategoryAdapter()

    }

    private fun createAdapters(){
        categoryAdapter = CategoriesAdapter(this)
        drinkAdapter = DrinkAdapter(this)
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu);

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView

        setOnActionExpandListener(searchItem, searchView)

        setOnQueryTextListener(searchView)

    }

    private fun setOnActionExpandListener(searchItem: MenuItem?, searchView: SearchView?){

        searchItem?.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                searchView?.isIconified = false
                searchView?.requestFocusFromTouch()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                if(viewModel.getDrinkListSize() <= 0){
                    setCategoryAdapterAndConfig()
                }
                return true
            }
        })

    }

    private fun setOnQueryTextListener(searchView: SearchView?){
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(s: String): Boolean {
                return handleQueryChange(s) // If this doesn't work add return false below
            }
        })
    }

    private fun handleQueryChange(query: String): Boolean{
        if (query.isEmpty()) {
            setCategoryAdapterAndConfig()
        } else {
            setDrinkAdapterAndConfig(query)
        }
        return false
    }

    private fun setCategoryAdapterAndConfig(){
        setCategoryAdapter()
        setCategoryAdapterSettings()
    }

    private fun setCategoryAdapter(){
        getRecyclerView().adapter = categoryAdapter
    }

    private fun setCategoryAdapterSettings(){
        setLayoutSpanCount(getLayoutManager(),1)

    }

    private fun setDrinkAdapterAndConfig(query: String){

        if(!currentAdapterIsDrink()){
            setDrinkAdapter()
            setDrinkAdapterSettings()
        }
        viewModel.getDrinkList(query).observe(viewLifecycleOwner, Observer {
            updateDrinkAdapterDrinkList(it)
        })
    }

    private fun setDrinkAdapter(){
        getRecyclerView().adapter = drinkAdapter
    }

    private fun setDrinkAdapterSettings(){
        setLayoutSpanCount(getLayoutManager(),3)
    }

    private fun currentAdapterIsDrink(): Boolean{
        return getRecyclerView().adapter == drinkAdapter
    }

    private fun updateDrinkAdapterDrinkList(drinkList: List<Drink>){
        drinkAdapter.setDrinks(drinkList)
    }

    private fun getLayoutManager(): GridLayoutManager{
        return getRecyclerView().layoutManager as GridLayoutManager
    }

    private fun setLayoutSpanCount(layout: GridLayoutManager, spanCount: Int){
        layout.spanCount = spanCount
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}