package com.example.sampleapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.adapter.DataAdapter
import com.example.sampleapp.model.GitHubResponseModel
import com.example.sampleapp.model.ItemData
import com.example.sampleapp.network.NetworkResult
import com.example.sampleapp.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val KOTLIN_REPO_STRING: String = "kotlin"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {
    private val _response: MutableLiveData<NetworkResult<GitHubResponseModel>> = MutableLiveData()
    val response: LiveData<NetworkResult<GitHubResponseModel>> = _response

    fun makeApiCall() = viewModelScope.launch {
        repository.getAllRepository(KOTLIN_REPO_STRING).collect { values ->
            _response.value = values
        }
    }

    private var dataAdapter: DataAdapter = DataAdapter()

    fun getAdapter(): DataAdapter {
        return dataAdapter
    }

    fun setAdapterData(data: ArrayList<ItemData>?) {
        data?.let {
            dataAdapter.setData(data)
            dataAdapter.notifyDataSetChanged()
        }
    }
}