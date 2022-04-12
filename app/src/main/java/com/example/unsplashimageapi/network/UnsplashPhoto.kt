package com.example.unsplashimageapi.network


/**
 * The data class that defines the structure of the JSON objects received
 */
data class UnsplashPhoto(
    var total : Int ,
    var total_pages: Int,
    var results: List<Result>
    )

data class Result(
    var description : String,
    var alt_description : String,
    var url : Url
)

/**
 * In the hierarchy of objects, we need the Url
 * to get the links for the images and in their various sizes
 */
data class Url(
    var raw : String,
    var full : String,
    var regular : String,
    var small : String,
    var thumb : String,
    var small_s3 : String
    )
