package com.br.events.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.events.data.model.Event
import com.br.events.databinding.EventItemBinding
import com.br.events.ui.util.loadImage
import com.br.events.ui.util.toDateFormatted

class EventAdapter (private val events: List<Event>, private val onItemClick: ((event: Event) -> Unit)): RecyclerView.Adapter<EventAdapter.EventViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindView(events[position])
    }

    override fun getItemCount(): Int {
        return events.count()
    }

    inner class EventViewHolder(private val binding : EventItemBinding, private val onItemClick: (event: Event) -> Unit): RecyclerView.ViewHolder(binding.root){

        fun bindView(event: Event) {
            binding.imageViewPost.loadImage(event.image)
            binding.textViewTitle.text = event.title
            binding.textViewDate.text = event.date.toDateFormatted()
            binding.textViewDescription.text = event.description


            itemView.setOnClickListener{
                onItemClick.invoke(event)
            }
        }

    }

}