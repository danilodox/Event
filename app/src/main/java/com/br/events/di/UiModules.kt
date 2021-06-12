package com.br.events.di

import com.br.events.ui.event.EventViewModel
import com.br.events.ui.eventCheckin.CheckinViewModel
import com.br.events.ui.eventDetails.EventDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { EventViewModel( get()) }
    viewModel { EventDetailsViewModel( get()) }
    viewModel { CheckinViewModel( get()) }
}