package com.example.thecocktaildb.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktaildb.databinding.CategoryCardViewBinding
import com.example.thecocktaildb.ui.util.OnClickElementInterface


class CategoriesAdapter() :
    RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    private var categoryList: List<String> = ArrayList()

    lateinit var mItemClickListener: OnClickElementInterface

    constructor(mItemClickListener: OnClickElementInterface) : this() {
        this.mItemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CategoryCardViewBinding.inflate(inflater,parent,false)

        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    fun updateCategories(categoryList: List<String>){
        this.categoryList = categoryList
        this.notifyDataSetChanged()
    }



    inner class CategoryHolder(binding: CategoryCardViewBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val binding: CategoryCardViewBinding

        init{
            this.binding = binding
            this.binding.root.setOnClickListener(this)
        }

        fun bind(name: String){
            setName(name)
        }

        private fun setName(name: String){
            binding.categoryName.text = name
        }

        override fun onClick(v: View?) {
            Log.d("TESTING__", "click cat_adapter: $adapterPosition")
            mItemClickListener.onItemClick(adapterPosition)
        }
    }



}