package com.example.sampleapp.model

import com.google.gson.annotations.SerializedName

data class GitHubResponseModel(val items: ArrayList<ItemData>)
data class ItemData(
    val name: String?,
    @SerializedName("full_name") val fullName: String?,
    val url: String?,
    val language: String?,
    val forks: String?,
    val description: String?,
    @SerializedName("created_at") val createdAt: String?,
    val owner: Owner
)

data class Owner(@SerializedName("avatar_url") val avatarUrl: String?)
