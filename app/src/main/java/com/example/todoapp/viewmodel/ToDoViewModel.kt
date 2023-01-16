package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.db.ToDoDatabase
import com.example.todoapp.db.ToDoList
import com.example.todoapp.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    val todoList: LiveData<List<ToDoList>>
    val repository: ToDoRepository
    val title=MutableLiveData("")
    val description = MutableLiveData("")

    init {
        val dao = ToDoDatabase.getInstance(application).todoDao()
        repository = ToDoRepository(dao)
        todoList = repository.getToDoList
    }

    fun insert(list: ToDoList)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(list)
    }

    fun update(list: ToDoList) =viewModelScope.launch(Dispatchers.IO) {
        repository.update(list)
    }

    fun deleteList(list: ToDoList) =viewModelScope.launch(Dispatchers.IO) {
        repository.deleteList(list)
    }

    fun clearText(){
        title.postValue("")
        description.postValue("")

    }

}