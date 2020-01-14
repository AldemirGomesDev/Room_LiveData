package com.aldemir.roomelivedata.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NotesEntity(
    @PrimaryKey
    @ColumnInfo(name = "texto")
    var texto : String = "",
    var prioridade : String = "")
