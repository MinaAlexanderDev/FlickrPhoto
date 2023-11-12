package com.example.moviesphoto.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.moviesphoto.data.model.Photo
import com.example.moviesphoto.util.Constants.TABLE_MOVIES_PHOTO

@Dao
interface MoviesPhotoDao {

    @Query("SELECT * FROM $TABLE_MOVIES_PHOTO ")
    fun moviesPhotoPagingSource(): PagingSource<Int, Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Photo>)

    @Query("DELETE FROM $TABLE_MOVIES_PHOTO ")
    suspend fun clearAll()

    @Query("DELETE FROM $TABLE_MOVIES_PHOTO ")
    fun clearPrimaryKey()
}
