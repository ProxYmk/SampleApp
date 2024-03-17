package com.example.sampleapp.model

import com.google.gson.annotations.SerializedName

data class ItemData(
    val name: String?,
    @SerializedName("full_name") val fullName: String?,
    val url: String?,
    val language: String?,
    val forks: Int?,
    val description: String?,
    @SerializedName("created_at") val createdAt: String?,
    val owner: Owner
)