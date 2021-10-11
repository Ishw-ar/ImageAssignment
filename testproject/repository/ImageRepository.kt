package com.karan.testproject.repository


import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.karan.testproject.api.ImageService


import com.karan.testproject.models.ImageResponse
import com.karan.testproject.pagingSource.ImagePagingSource
import com.karan.testproject.viewmodels.ImageViewModel
import retrofit2.Callback
import retrofit2.Response


/**
 * This is Repository class which is responsible for interacting with the model class
 */
class ImageRepository (private val imageService: ImageService) {
    fun getPhotosId()=
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(imageService) }
        ).liveData
}