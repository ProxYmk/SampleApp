package com.example.sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sampleapp.model.ItemData
import com.example.sampleapp.model.Owner
import com.example.sampleapp.viewmodel.SharedViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: SharedViewModel

    @Before
    fun setUp() {
        viewModel = SharedViewModel()
    }

    @Test
    fun `selectItem updates SelectedItem`() {
        // Given test data
        val testItem = ItemData(
            name = "name",
            fullName = "full name",
            url = "url",
            language = "language",
            forks = 1,
            description = "description",
            createdAt = "created at",
            Owner("https://secure.gravatar.com/avatar/420.png")
        )
        // set observers
        val observer: Observer<ItemData> = mock(Observer::class.java) as Observer<ItemData>
        viewModel.selectedItem.observeForever(observer)

        // When
        viewModel.selectItem(testItem)

        // Then verify data change
        verify(observer).onChanged(testItem)
    }
}