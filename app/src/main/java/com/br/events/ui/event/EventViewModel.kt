package com.br.events.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.events.data.model.Event
import com.br.events.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    val mEventsLiveData : MutableLiveData<List<Event>> = MutableLiveData()
    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorEventDetailLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getEvents(){
        loadingLiveData.value = true
        errorEventDetailLiveData.value = false

        viewModelScope.launch {
            try {
                mEventsLiveData.value = repository.getEvents()

            } catch (e : Exception){
                errorEventDetailLiveData.value = true
            }
            loadingLiveData.value = false

        }
    }
}