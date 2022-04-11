package com.example.unsplashimageapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.unsplash.com/"

/**
 * Build the Moshi object that retrofit wil be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))


interface UnsplashApiService {
    /**
     * Returns a single unsplashPhoto
     * The @GET annotation indicates the
     */
    @GET("photos/random")
    suspend fun getPhotos(): UnsplashPhoto

}