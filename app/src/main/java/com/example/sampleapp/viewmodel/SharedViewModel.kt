package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.model.ItemData

class SharedViewModel : ViewModel(){
    private val _selectedItem = MutableLiveData<ItemData>()
    val selectedItem: LiveData<ItemData> get() = _selectedItem

    fun selectItem(item: ItemData) {
        _selectedItem.value = item
    }
}