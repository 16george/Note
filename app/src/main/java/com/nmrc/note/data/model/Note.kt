package com.nmrc.note.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nmrc.note.viewmodel.NoteSharedViewModel
import java.time.LocalDateTime

@Entity(tableName = "notes")
data class Note (

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "date")
        var date: LocalDateTime,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean,

        @ColumnInfo(name = "image_path")
        var image: String? = null,

        @PrimaryKey(autoGenerate = true)
        val id: Int = 0
        )
{
        constructor(data: NoteSharedViewModel.RecoverNoteData) : this(
                data.title,
                data.date,
                data.description,
                data.favorite,
                data.image
        )
}