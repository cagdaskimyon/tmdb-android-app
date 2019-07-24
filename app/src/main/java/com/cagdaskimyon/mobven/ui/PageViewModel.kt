package com.cagdaskimyon.mobven.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()

    fun setIndex(index: Int) {
        _index.value = index
    }
}