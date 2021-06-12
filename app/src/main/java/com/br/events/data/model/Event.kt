package com.br.events.data.model

data class Event(
    val id: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: String,
    val latitude: String,
    val price: Float,
    val title: String
)