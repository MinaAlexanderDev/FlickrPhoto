package com.example.moviesphoto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.moviesphoto.data.model.Photo

@Database(
    entities = [Photo::class],
    version = 1,
)
abstract class MoviesPhotoDatabase : RoomDatabase() {
    abstract fun getMoviesPhotoDao(): MoviesPhotoDao
}
