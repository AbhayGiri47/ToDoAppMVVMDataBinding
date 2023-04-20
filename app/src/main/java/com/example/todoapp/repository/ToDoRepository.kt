package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.db.ToDoDao
import com.example.todoapp.db.ToDoList

class ToDoRepository(private val todoDao: ToDoDao) {

    val getToDoList : LiveData<List<ToDoList>> = todoDao.getToDolist()

    suspend fun insert(list: ToDoList){
        todoDao.insert(list)
    }

    suspend fun update(list: ToDoList){
        todoDao.update(list)
    }

    suspend fun deleteList(list: ToDoList){
        todoDao.deleteList(list)
    }

    suspend fun deleteAllList(){
        todoDao.deleteAllList()
    }
}