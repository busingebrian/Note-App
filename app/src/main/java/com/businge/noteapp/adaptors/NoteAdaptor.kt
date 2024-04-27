package com.businge.noteapp.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.businge.noteapp.R
import com.businge.noteapp.room.Note

class NoteAdaptor(val context: Context, val notesList: MutableList<Note>) : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder> (){

    inner class NoteViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textVieTitle = view.findViewById(R.id.text_view_title) as TextView // casting
        val textViewDescription: TextView = view.findViewById(R.id.text_view_description)
        val textViewPriority: TextView = view.findViewById(R.id.text_view_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item,parent, false))
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.textVieTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }
}