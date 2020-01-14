package com.aldemir.roomelivedata.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.aldemir.roomelivedata.model.NotesEntity

@Dao
interface NotesDao {

    @Query("SELECT * from notesTable")
    fun getAllNotes() : List<NotesEntity>

    @Query("SELECT * from notesTable")
    fun getAll() : LiveData<List<NotesEntity>>

    @Insert
    fun insertNote(notes : NotesEntity)

    @Delete
    fun deleteNote(note : NotesEntity)

}