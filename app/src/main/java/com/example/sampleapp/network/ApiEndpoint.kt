package com.example.sampleapp.network

import com.example.sampleapp.model.GitHubResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("search/repositories")
    suspend fun getAllRepository(@Query("q") q: String) : Response<GitHubResponseModel>
}