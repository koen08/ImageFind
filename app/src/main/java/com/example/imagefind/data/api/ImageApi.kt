package com.example.imagefind.data.api

import com.example.imagefind.data.network.models.ImageListNet
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("/api/")
    fun getImageList(
        @Query("key") key: String,
        @Query("q") param: String,
        @Query("orientation") orientation: String,
        @Query("page") page: Int
    ): Single<ImageListNet>
}