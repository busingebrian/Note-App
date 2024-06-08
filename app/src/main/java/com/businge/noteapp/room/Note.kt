package com.businge.noteapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    // @ColumnInfo(name = "title_column") -> used to change the column names
    val title: String,
    val description: String,
    val priority: Int
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}