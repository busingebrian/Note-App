package com.businge.noteapp.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            //if the INSTANCE is null, then return it
            // if it is, then create the instance

            return INSTANCE ?: synchronized(this) { // synchronized means only one thread can access the instance at a time
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}