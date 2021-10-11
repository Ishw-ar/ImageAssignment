package com.karan.testproject.api

import com.karan.testproject.models.ImageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("services/rest/")
    suspend fun getPhotosId(
        @Query("method") method:String,
        @Query("api_key") api_key:String,
        @Query("format") format:String,
        @Query("nojsoncallback") nojsoncallback:Int,
        @Query("safe_search") safe_search:Int,
        @Query("tags") tags:String,
        @Query("per_page") per_page:Int,
        @Query("page") page:Int
    ):ImageResponse
}