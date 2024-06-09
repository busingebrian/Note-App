package com.businge.noteapp.utils.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.businge.noteapp.R
import com.businge.noteapp.utils.Constants

class AddEditActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var numberPicker: NumberPicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        editText = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPicker = findViewById(R.id.number_picker_priority)

        numberPicker.minValue = 0
        numberPicker.maxValue = 10

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // ill help enable the icon appear in the menu on the lhs
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        if (intent.hasExtra(Constants.EXTRA_ID)) {
            title = "Edit Note"
            editText.setText(intent.getStringExtra(Constants.EXTRA_TITLE))
            editTextDescription.setText(intent.getStringExtra(Constants.EXTRA_DESCRIPTION))
            numberPicker.value = intent.getIntExtra(Constants.EXTRA_PRIORITY, 1)
        } else {
            title = "Add Note"
        }

    }

    private fun saveNote() {
        val title = editText.text.toString()
        val description = editTextDescription.text.toString()
        val priority = numberPicker.value

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert Title and description 😢", Toast.LENGTH_LONG).show()
            return
        }
        //make sure you send the data back from the addEdit activity

        val id = intent.getIntExtra(Constants.EXTRA_ID, -1)
        if (id != -1) {
            setResult(Constants.EDIT_REQUEST_CODE, Intent().apply {
                putExtra(Constants.EXTRA_ID, id)
                putExtra(Constants.EXTRA_TITLE, title)
                putExtra(Constants.EXTRA_DESCRIPTION, description)
                putExtra(Constants.EXTRA_PRIORITY, priority)
            })
        } else {
            setResult(Constants.ADD_REQUEST_CODE, Intent().apply {
                putExtra(Constants.EXTRA_TITLE, title)
                putExtra(Constants.EXTRA_DESCRIPTION, description)
                putExtra(Constants.EXTRA_PRIORITY, priority)
            })
        }
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_menu_item -> {
                saveNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}