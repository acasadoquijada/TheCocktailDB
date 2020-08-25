package com.example.thecocktaildb.ui.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class DataBindingAbstractFragment: Fragment() {

    abstract fun setupDatabinding(inflater: LayoutInflater, container: ViewGroup?)
    abstract fun getRootView(): View

}