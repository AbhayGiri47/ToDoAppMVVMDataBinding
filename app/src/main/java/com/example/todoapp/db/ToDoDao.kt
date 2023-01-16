package com.example.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: ToDoList)

    @Update
    suspend fun update(list: ToDoList)

    @Query("Select * from todoTable")
    fun getToDolist(): LiveData<List<ToDoList>>

    @Delete
    suspend fun deleteList(list: ToDoList)
}