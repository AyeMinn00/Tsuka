package com.ayeminoo.tsuka.data.api

import com.ayeminoo.tsuka.data.api.services.OpenExchangeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit


class OpenExchangeApiServiceTest {

    private lateinit var apiService: OpenExchangeApiService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(OpenExchangeApiService::class.java)
    }

    @After
    fun clear() {
        mockWebServer.shutdown()
    }

    @Test
    fun success() = runTest {
        enqueueResponse("rates.json")
        val response = apiService.fetchLatest()
        val request = mockWebServer.takeRequest()
        assertEquals(request.path, "/api/latest.json")
        assertNotNull(response)
        assertNotNull(response.body())
        val content = response.body()
        assertEquals("GET", request.method)
        assertNotNull(content)
        assertEquals(3, content!!.rates.size)
        assertEquals("USD", content.base)
        assertEquals(1691319618, content.timeStamp)
    }

    @Test(expected = Exception::class)
    fun malformedJson() = runTest {
        enqueueResponse("malformed.json")
        apiService.fetchLatest()
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-responses/$fileName")
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }


}