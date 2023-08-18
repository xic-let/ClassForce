package com.example.classforce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemListViewModel : ViewModel() {
    private val itemList: MutableList<Any> = mutableListOf()

    private val _itemsLiveData = MutableLiveData<List<Any>>()
    val itemsLiveData: LiveData<List<Any>> = _itemsLiveData

    init {
        _itemsLiveData.value = itemList
        initializeData()
    }

    private fun initializeData() {
        val initialData = listOf("Item 1", "Item 2", "Item 3")
        itemList.addAll(initialData)
        _itemsLiveData.value = itemList
    }

    fun submitList(newItems: List<Any>?) {
        newItems?.let {
            itemList.clear()
            itemList.addAll(newItems)
            _itemsLiveData.value = itemList
        }
    }
}