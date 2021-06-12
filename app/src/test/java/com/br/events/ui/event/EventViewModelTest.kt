package com.br.events.ui.event

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.events.data.FakeData
import com.br.events.data.model.Event
import com.br.events.data.repository.EventRepository
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class EventViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk<EventRepository>()
    private val eventsLiveDataObserver = mockk<Observer<List<Event>>>(relaxed = true)
    private val loadingLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `call events with success then set eventsLiveData and show loading`() {

        val viewModel = instantiateViewModel()
        coEvery { repository.getEvents() } throws Exception()

        viewModel.getEvents()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(FakeData.IS_LOADING)
            repository.getEvents()
            loadingLiveDataObserver.onChanged(FakeData.NOT_LOADING)
        }
        confirmVerified(loadingLiveDataObserver)
        confirmVerified(repository)
        confirmVerified(loadingLiveDataObserver)


    }


    private fun instantiateViewModel(): EventViewModel {
        val viewModel =
            EventViewModel(repository)
        viewModel.mEventsLiveData.observeForever(eventsLiveDataObserver)
        viewModel.loadingLiveData.observeForever(loadingLiveDataObserver)
        return viewModel
    }
}