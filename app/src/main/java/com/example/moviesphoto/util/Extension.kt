package com.example.moviesphoto.util


fun createMoviePhotoUrl(farm: Int, server: String, id: String, secret: String): String {

    return "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
}
