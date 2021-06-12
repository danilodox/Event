package com.br.events.data.network

import com.br.events.data.model.Checkin
import com.br.events.data.model.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/events")
    suspend fun getEvents(): List<Event>

    @GET("/events/{id}")
    suspend fun getEvent(@Path("id") id: String): Event

    @POST("/checkin")
    suspend fun postCheckIn(@Body checkin: Checkin)
}