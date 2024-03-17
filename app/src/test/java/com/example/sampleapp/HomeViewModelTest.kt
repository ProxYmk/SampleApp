package com.example.sampleapp
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleapp.data.adapter.DataAdapter
import com.example.sampleapp.model.GitHubResponseModel
import com.example.sampleapp.model.ItemData
import com.example.sampleapp.model.Owner
import com.example.sampleapp.network.NetworkResult
import com.example.sampleapp.repository.GitHubRepository
import com.example.sampleapp.viewmodel.HomeViewModel
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: DataAdapter
    private val testDispatcher = UnconfinedTestDispatcher()
    private val repository: GitHubRepository = mockk()

    private fun readJsonFromResource(): String {
        return this::class.java.classLoader?.getResource("mock_response.json")?.readText() ?: ""
    }
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        adapter = mock(DataAdapter::class.java) // Mock the adapter
        val mockJsonResponse = readJsonFromResource()
        // Convert JSON string to GitHubResponseModel object
        val gson = Gson()
        val mockResponse = gson.fromJson(mockJsonResponse, GitHubResponseModel::class.java)

        // Mock the repository's behavior
        coEvery { repository.getAllRepository(any()) } returns flowOf(NetworkResult.Success(mockResponse))
        // Initialize ViewModel
        viewModel = HomeViewModel(repository)
        // Set mock adapter
        viewModel.dataAdapter = adapter
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher to the original state
        Dispatchers.resetMain()
    }

    @Test
    fun `getRepositories updates LiveData with Success`() = runTest {
        // Given a ViewModel with a mocked repository onSetup
        // When making an API call getRepositories
        launch {
            viewModel.getRepositories()
        }

        // Then the response LiveData should be updated with success
        viewModel.response.observeForever {
            assertNotNull(it)
            assertTrue(it is NetworkResult.Success)
        }
    }

    @Test
    fun `setAdapterData updates data in adapter`() {
        // Given some test data
        val testData = arrayListOf(
            ItemData(
                name = "name",
                fullName = "full name",
                url = "url",
                language = "language",
                forks = 1,
                description = "description",
                createdAt = "created at",
                Owner("https://secure.gravatar.com/avatar/420.png")
            )
        )
        // When setting adapter data
        viewModel.setAdapterData(testData)
        // then verify if DataSetChanged
        verify(adapter).notifyDataSetChanged()
    }
}