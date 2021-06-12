package com.br.events.ui.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.events.databinding.ActivityEventBinding
import com.br.events.ui.eventDetails.EventDetailsActivity
import com.br.events.ui.util.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventActivity: AppCompatActivity() {

    private val mViewModel : EventViewModel by viewModel()
    private lateinit var binding : ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUiElements()
        showLoading()
        showError()
    }

    private fun initUiElements() {
        initRecyclerObserver()

    }



    private fun initRecyclerObserver(){
        mViewModel.mEventsLiveData.observe(this, Observer {
            it?.let {posts ->
                with(binding.recyclerListPost){
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = EventAdapter(posts) {
                        EventDetailsActivity.start(this@EventActivity, it.id)
                    }
                }
            }
        })
        mViewModel.getEvents()
    }

    private fun showLoading(){
        mViewModel.loadingLiveData.observe(this, Observer {
            binding.progressLoadingEvent.root.isVisible(it)
        })
    }

    private fun showError(){
        mViewModel.errorEventDetailLiveData.observe(this, Observer {
            binding.errorNetworkEvent.root.isVisible(it)
        })
    }
}