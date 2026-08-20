package com.example.mininetflixx.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable // <--- AGREGAMOS ESTE IMPORT

data class Video(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("imageUrl") val imageUrl: String
) : Serializable // <--- AGREGAMOS ESTO AL FINAL PARA QUE SEA SERIALIZABLE