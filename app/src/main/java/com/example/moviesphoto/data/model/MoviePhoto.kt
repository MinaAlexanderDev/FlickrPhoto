package com.example.moviesphoto.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesphoto.util.Constants
import com.google.gson.annotations.SerializedName


data class MoviePhoto(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)

data class Photos(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val perpage: Int,
    @SerializedName("photo")
    val photo: List<Photo>,
    @SerializedName("total")
    val total: Int
)

@Entity(tableName = Constants.TABLE_MOVIES_PHOTO)
data class Photo(
    @SerializedName("farm")
    val farm: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("isfamily")
    val isfamily: Int,
    @SerializedName("isfriend")
    val isfriend: Int,
    @SerializedName("ispublic")
    val ispublic: Int,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("title")
    val title: String,
    @PrimaryKey(autoGenerate = true)
    var rowId: Int,
)