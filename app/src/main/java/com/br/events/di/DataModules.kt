package com.br.events.di


import com.br.events.data.network.RetrofitService
import com.br.events.data.repository.EventRepository
import com.br.events.data.repository.EventRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    factory { RetrofitService() }

    /*factory {
        provideHttpClient()
    }
    single {
        createWebService<ApiService>(
            okHttpClient = get()
        )
    }*/

    factory <EventRepository> { EventRepositoryImpl( get()) }





}