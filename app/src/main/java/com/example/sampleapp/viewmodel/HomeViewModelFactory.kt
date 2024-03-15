package com.example.sampleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.repository.GitHubRepository
import com.example.sampleapp.repository.GitHubRepositoryImpl
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val repository: GitHubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}