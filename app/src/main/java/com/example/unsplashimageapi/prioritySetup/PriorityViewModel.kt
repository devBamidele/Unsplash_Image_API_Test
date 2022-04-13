package com.example.unsplashimageapi.prioritySetup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashimageapi.network.UnsplashApi
import com.example.unsplashimageapi.network.Model
import kotlinx.coroutines.launch

/**
 * A set of constants that will show the status of the API
 */
enum class UnsplashApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [PriorityFragment]
 */
class PriorityViewModel : ViewModel() {

    // The search request of the user
    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    // To store the kotlin objects received in the predefined format
    private val _photos = MutableLiveData<Model.UnsplashPhoto>()
    val photos: LiveData<Model.UnsplashPhoto> = _photos


    // For error handing
    private val _jsonString = MutableLiveData<String>()
    val jsonString: LiveData<String> = _jsonString


    private val _url = MutableLiveData<Model.Url>()
    val url : LiveData<Model.Url> = _url


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
        getUnsplashPhoto()
        _query.value = ""
        //_jsonString.value = "Just to check if the Json string works"
    }

    /**
     * Return a list of UnsplashPhotos
     */
    private fun getUnsplashPhoto() {
        viewModelScope.launch {
            _jsonString.value = UnsplashApiStatus.LOADING.toString()
            try {
                _photos.value = UnsplashApi.retrofitService.getPhotos()
                _jsonString.value = UnsplashApiStatus.DONE.toString()
            } catch (e : Exception) {
                _jsonString.value = e.localizedMessage
            }
        }
    }
// the error message was "Required value 'url' missing at $.results[1]

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