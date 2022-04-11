package com.example.unsplashimageapi.prioritySetup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


enum class UnsplashApiStatus { LOADING, ERROR, DONE }

/**
 * The [viewModel] that is attached to the [PriorityFragment]
 */
class PriorityViewModel : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    fun setQuery(query: String){
        _query.value = query
    }

    /**
     * Check if the query text meets some criteria
     */
    fun confirmValid(name: String): Boolean {
        return name.length >= 3
                && !name.contains("[!\"#$%&'()*+,-/:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

}