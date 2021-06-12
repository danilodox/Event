package com.br.events.ui.eventDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.events.data.model.Event
import com.br.events.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventDetailsViewModel(private val repository: EventRepository) : ViewModel()  {

    val mEventsLiveData : MutableLiveData<Event> = MutableLiveData()
    val loadingDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorEventDetailLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getEvent(id : String){
        loadingDetailsLiveData.value = true
        errorEventDetailLiveData.value = false

        viewModelScope.launch {
            try {
                mEventsLiveData.value = repository.getEvent(id)

            } catch (e : Exception){
                errorEventDetailLiveData.value = true
            }
            loadingDetailsLiveData.value = false

        }
    }

    fun getShareText(): String {
        var shareText = "${mEventsLiveData.value?.title}\n\n"
        shareText += mEventsLiveData.value?.description
        return shareText
    }
}