package com.businge.noteapp.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.businge.noteapp.R
import com.businge.noteapp.room.Note

class NoteAdaptor(val listener: OnClickListener) :
    ListAdapter<Note, NoteAdaptor.NoteViewHolder>(DIFF_CALLBACK) { // Diff_callback can only be accessed when we use the companion object to create an instance of it

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
//                    return oldItem.title == newItem.title && oldItem.description == newItem.description
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.textVieTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }


    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textVieTitle: TextView = view.findViewById(R.id.text_view_title)
        val textViewDescription: TextView = view.findViewById(R.id.text_view_description)
        val textViewPriority: TextView = view.findViewById(R.id.text_view_priority)

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {// check if the position is valid
                    listener.onClickItem(getItem(adapterPosition))// get the note at the position
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(note: Note)
    }
}