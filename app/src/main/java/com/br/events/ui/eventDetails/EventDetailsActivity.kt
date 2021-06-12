package com.br.events.ui.eventDetails


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.br.events.databinding.ActivityEventDetailsBinding
import com.br.events.ui.eventCheckin.CheckinActivity
import com.br.events.ui.util.loadImage
import com.br.events.ui.util.toDateFormatted
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailsActivity : AppCompatActivity(){

    private val mViewModel : EventDetailsViewModel by viewModel()
    private lateinit var binding : ActivityEventDetailsBinding

    companion object {
        const val EVENT_ID = "idEvent"
        fun start(context: Context, eventId: String) {
            val intent =
                Intent(context, EventDetailsActivity::class.java).apply {
                    putExtra(EVENT_ID, eventId)
                }
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        intent.getStringExtra(EVENT_ID)?.let { mViewModel.getEvent(it) }
        onClickShare()
        observerEventDetails()
        initCheckin()

    }


    private fun observerEventDetails() {
        mViewModel.mEventsLiveData.observe(this, Observer {
            it?.let {event ->
                binding.imageViewMainPost.loadImage(event.image)
                binding.textViewTitle.text = event.title
                binding.textViewDate.text = event.date.toDateFormatted()
                binding.textViewPrice.text = event.price.toString()
                binding.textViewDescription.text = event.description
            }
        })
        intent.getStringExtra(EVENT_ID)?.let { mViewModel.getEvent(it) }
    }

    private fun initCheckin(){
        binding.buttonCheckin.setOnClickListener {
            mViewModel.mEventsLiveData.observe(this, Observer {
                val intent = Intent(this@EventDetailsActivity, CheckinActivity::class.java).apply {
                    putExtra(EVENT_ID, it.id)
                }
                startActivity(intent)
            })

        }
    }

    private fun onClickShare() {
        binding.buttonShare.setOnClickListener {

            val shareText = mViewModel.getShareText()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

    }

    private fun initToolbar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}