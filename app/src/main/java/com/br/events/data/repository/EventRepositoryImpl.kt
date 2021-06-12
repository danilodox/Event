package com.br.events.data.repository

import com.br.events.data.model.Checkin
import com.br.events.data.model.Event
import com.br.events.data.network.ApiService
import com.br.events.data.network.RetrofitService
import java.lang.Exception

class EventRepositoryImpl(private val retrofitService: RetrofitService): EventRepository {

    override suspend fun getEvents(): List<Event>? {
        return try { retrofitService.service.getEvents() }
        catch (e: Exception) { null }
    }

    override suspend fun getEvent(id: String): Event {
        return retrofitService.service.getEvent(id)
    }

    override suspend fun postCheckin(request: Checkin) {
        return retrofitService.service.postCheckIn(request)
    }
}