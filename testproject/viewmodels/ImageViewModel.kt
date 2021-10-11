package com.karan.testproject.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.karan.testproject.models.ImageResponse
import com.karan.testproject.models.Photo
import com.karan.testproject.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * ViewModel class is responsible for interacting with the repository class
 */
class ImageViewModel(private val repository: ImageRepository) : ViewModel() {
    fun fetchImagesLiveData(): LiveData<PagingData<Photo>> {
        return repository.getPhotosId()
            .map { data -> data.map { it } }
            .cachedIn(viewModelScope)
    }
}