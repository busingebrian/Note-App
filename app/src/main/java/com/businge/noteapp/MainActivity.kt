package com.businge.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.businge.noteapp.adaptors.NoteAdaptor
import com.businge.noteapp.room.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var notesAdaptor: NoteAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView = findViewById(R.id.recycle_View)
        notesAdaptor = NoteAdaptor()
        recycleView.adapter = notesAdaptor
        recycleView.layoutManager = LinearLayoutManager(this)

        noteViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                NoteViewModel::class.java
            )
        noteViewModel.allNotes.observe(this){
            // here we can add the data to our recycleView
            notesAdaptor.setNotes(it)
        }
    }
}