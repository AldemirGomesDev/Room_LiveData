package com.aldemir.roomelivedata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aldemir.roomelivedata.dao.NotesDao
import com.aldemir.roomelivedata.model.NotesEntity

@Database(entities = arrayOf(NotesEntity::class), version = 1)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun DAO() : NotesDao

    companion object {
        private var INSTANCE : NotesDatabase? = null

        @Synchronized
        fun getInstance(context : Context) : NotesDatabase {

            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "database.db").build()
            }
            return INSTANCE as NotesDatabase
        }
    }
}