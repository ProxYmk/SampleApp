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
    fun testSetData_updatesItemListCorrectly() {
        // Given
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

        // When
        adapter.setData(ArrayList(testData))

        // Then
        val actualData = adapter.getData()
        assertEquals(testData, actualData)
    }
}