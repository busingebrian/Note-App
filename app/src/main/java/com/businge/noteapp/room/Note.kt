package com.businge.noteapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val description: String,
    val age: Int,
    val priority: Int
){
    @PrimaryKey(autoGenerate = true)
    val id = 0
}