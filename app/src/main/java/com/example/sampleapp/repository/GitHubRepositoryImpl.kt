package com.example.sampleapp.repository

import com.example.sampleapp.model.GitHubResponseModel
import com.example.sampleapp.network.ApiEndpoint
import com.example.sampleapp.network.BaseApiResponse
import com.example.sampleapp.network.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class GitHubRepositoryImpl @Inject constructor(
    private val apiEndpoint: ApiEndpoint
) : BaseApiResponse(), GitHubRepository {

    override suspend fun getAllRepository(query: String): Flow<NetworkResult<GitHubResponseModel>> {
        return flow {
            emit(safeApiCall { apiEndpoint.getAllRepository(query) })
        }.flowOn(Dispatchers.IO)
    }
}