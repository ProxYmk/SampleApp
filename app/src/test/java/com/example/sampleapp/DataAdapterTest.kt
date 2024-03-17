package com.example.sampleapp

import com.example.sampleapp.data.adapter.DataAdapter
import com.example.sampleapp.model.ItemData
import com.example.sampleapp.model.Owner
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DataAdapterTest {
    private lateinit var adapter: DataAdapter

    @Before
    fun setUp() {
        adapter = DataAdapter()
    }

    @Test
    fun `setData updates itemList correctly`() {
        // Given test data
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

        // When set fake data
        adapter.setData(ArrayList(testData))

        // Then check get same data
        val actualData = adapter.getData()
        assertEquals(testData, actualData)
    }
}