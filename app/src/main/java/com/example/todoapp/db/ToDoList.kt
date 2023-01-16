package com.example.todoapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*@Entity(tableName = "todoTable")
data class ToDoList(

    @PrimaryKey(autoGenerate = true)
    var id: Int =0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "time")
    val time: String,
)*/
@Entity(tableName = "todoTable")
class ToDoList(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timestamp") val time: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}

