package com.example.sampleapp.repository

import com.example.sampleapp.model.GitHubResponseModel
import com.example.sampleapp.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun getAllRepository(query: String): Flow<NetworkResult<GitHubResponseModel>>
}