package com.br.events.data.repository

import com.br.events.data.FakeData
import com.br.events.data.network.RetrofitService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class EventRepositoryFake {
    private val testDispatcher = TestCoroutineDispatcher()
    private val apiService : RetrofitService = mockk()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `call Events From Service`() = runBlockingTest{
        coEvery { apiService.service.getEvents() } returns FakeData.EVENT_LIST
        EventRepositoryImpl( apiService ).getEvents()
        coVerify { apiService.service.getEvents() }

    }

    @Test
    fun `call Event From Service`() =  runBlockingTest{
        coEvery { apiService.service.getEvent(FakeData.EVENT_DETAIL.id) } returns FakeData.EVENT_DETAIL
        EventRepositoryImpl( apiService ).getEvent(FakeData.EVENT_DETAIL.id)
        coVerify { apiService.service.getEvent(FakeData.EVENT_DETAIL.id) }
    }

    @Test
    fun `call Post Checkin From Service`() =  runBlockingTest{
        coEvery { apiService.service.postCheckIn(FakeData.CHECK_IN) } returns mockk()
        EventRepositoryImpl(apiService).postCheckin(FakeData.CHECK_IN)
        coVerify { apiService.service.postCheckIn(FakeData.CHECK_IN) }
    }
}