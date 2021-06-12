package com.br.events.data.repository

import com.br.events.data.model.Checkin
import com.br.events.data.model.Event

interface EventRepository {

    suspend fun getEvents(): List<Event>?
    suspend fun getEvent(id : String): Event
    suspend fun postCheckin(request: Checkin)
}