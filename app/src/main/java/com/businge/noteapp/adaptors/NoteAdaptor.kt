package com.businge.noteapp.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.businge.noteapp.R
import com.businge.noteapp.room.Note

class NoteAdaptor(val onClickListener: OnClickListener) : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>() {
    private var notesList: MutableList<Note> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.textVieTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }

    override fun getItemCount() = notesList.size

    fun setNotes(notes: MutableList<Note>) {
        notesList = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notesList[position]
    }
    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textVieTitle: TextView = view.findViewById(R.id.text_view_title)
        val textViewDescription: TextView = view.findViewById(R.id.text_view_description)
        val textViewPriority: TextView = view.findViewById(R.id.text_view_priority)
    }

    interface OnClickListener {
        fun onClickItem(note: Note)
    }
}