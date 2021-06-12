package com.br.events.ui.eventCheckin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.events.data.model.Checkin
import com.br.events.data.repository.EventRepository
import com.br.events.ui.util.Validation
import kotlinx.coroutines.launch

class CheckinViewModel(private val repository: EventRepository) : ViewModel() {


    val loadingDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val checkinResultLiveData: MutableLiveData<Boolean> = MutableLiveData()



    fun postCheckIn(eventId: String, userName: String, email: String) {
        loadingDetailsLiveData.value = true
        viewModelScope.launch {
            val checkIn = Checkin(eventId, userName, email)
            try {
                repository.postCheckin(checkIn)
                checkinResultLiveData.value = true
            } catch (ex: Exception) {
                checkinResultLiveData.value = false
            }
            loadingDetailsLiveData.value = false
        }
    }

    fun validateText(text: String) = text.isNotEmpty() && text.isNotBlank()

    fun validateEmail(email: String) = Validation.isEmailValid(email)





}