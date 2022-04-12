package com.example.unsplashimageapi.prioritySetup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashimageapi.network.UnsplashApi
import com.example.unsplashimageapi.network.UnsplashPhoto
import kotlinx.coroutines.launch

/**
 * A set of constants that will show the status of the API
 */
enum class UnsplashApiStatus { LOADING, ERROR, DONE }

/**
 * The [viewModel] that is attached to the [PriorityFragment]
 */
class PriorityViewModel : ViewModel() {

    // The search request of the user
    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    // The
    private val _photos = MutableLiveData<List<UnsplashPhoto>>()
    val photos: LiveData<List<UnsplashPhoto>> = _photos


    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<UnsplashApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<UnsplashApiStatus> = _status


    // A public method that exposes a private value
    fun setQuery(query: String){
        _query.value = query
    }

    /**
     * The constructor
     */
    init {
        _query.value = ""
    }

    /**
     * Return a single Unsplash Photo
     */
    private fun getUnsplashPhoto() {

        viewModelScope.launch {
            _status.value = UnsplashApiStatus.LOADING
            try {
                _photos.value = UnsplashApi.retrofitService.getPhotos()
                _status.value = UnsplashApiStatus.DONE
            } catch (e : Exception) {
                _status.value = UnsplashApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

    /**
     * Check if the query text meets some criteria
     */
    fun confirmValid(name: String?): Boolean {
        return  name!= null
                && name.length >= 2
                && name.length <= 30
                && !name.contains("[!\"#$%&'()*+,-/:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

}