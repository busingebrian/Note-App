package com.businge.noteapp.utils.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.businge.noteapp.R
import com.businge.noteapp.adaptors.NoteAdaptor
import com.businge.noteapp.room.Note
import com.businge.noteapp.room.NoteViewModel
import com.businge.noteapp.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdaptor: NoteAdaptor
    private lateinit var addNoteButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteButton = findViewById(R.id.add_note_button)

        recyclerView = findViewById(R.id.recycler_View)
        notesAdaptor = NoteAdaptor()
        recyclerView.adapter = notesAdaptor
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
            )[NoteViewModel::class.java]

        noteViewModel.allNotes.observe(this) {
            // here we can add the data to our recycleView
            notesAdaptor.setNotes(it)
        }

        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Constants.REQUEST_CODE) {
                    val title = it.data?.getStringExtra(Constants.EXTRA_TITLE)
                    val description = it.data?.getStringExtra(Constants.EXTRA_DESCRIPTION)
                    val priority = it.data?.getIntExtra(Constants.EXTRA_PRIORITY, -1)


                    val note = Note(title!!, description!!, priority!!)
                    noteViewModel.addNote(note)
                }
            }

        addNoteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            getResult.launch(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.deleteNote(notesAdaptor.getNoteAt(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_note_menu -> {
                noteViewModel.deleteAllNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}