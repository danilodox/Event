package com.br.events.ui.checkin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.events.data.FakeData
import com.br.events.data.repository.EventRepository
import com.br.events.ui.eventCheckin.CheckinViewModel
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

class CheckinViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = mockk<EventRepository>()

    private val checkinResultLiveData = mockk<Observer<Boolean>>(relaxed = true)
    private val loadingDetailsLiveData = mockk<Observer<Boolean>>(relaxed = true)

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
    fun `call post checkin with success then set checkinLiveData and show loading`(){
        val viewModel = instantiateViewModel()
        coEvery { repository.postCheckin(FakeData.CHECK_IN) } returns mockk()

        viewModel.postCheckIn(FakeData.CHECK_IN.id!!, FakeData.CHECK_IN.name, FakeData.CHECK_IN.email)

        coVerifyOrder {
            loadingDetailsLiveData.onChanged(FakeData.IS_LOADING)
            repository.postCheckin(FakeData.CHECK_IN)
            checkinResultLiveData.onChanged(FakeData.IS_CHECK_IN)
            loadingDetailsLiveData.onChanged(FakeData.NOT_LOADING)
        }

        confirmVerified(loadingDetailsLiveData)
        confirmVerified(repository)
        confirmVerified(checkinResultLiveData)


    }

    private fun instantiateViewModel(): CheckinViewModel {
        val viewModel =
            CheckinViewModel(repository)
        viewModel.checkinResultLiveData.observeForever(checkinResultLiveData)
        viewModel.loadingDetailsLiveData.observeForever(loadingDetailsLiveData)
        return viewModel
    }
}