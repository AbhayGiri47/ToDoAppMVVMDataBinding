package com.example.todoapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.adapter.ToDoAdapter
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.db.ToDoList
import com.example.todoapp.util.DateUtil
import com.example.todoapp.util.DateUtil.hideKeyboard
import com.example.todoapp.util.DateUtil.toString
import com.example.todoapp.viewmodel.ToDoViewModel

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ToDoAdapter
    lateinit var viewModel: ToDoViewModel
    lateinit var binding: ActivityMainBinding
    var isEdited = false
    var id = -1
    var title = ""
    var description = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = ToDoAdapter(::onDeleteCLick, ::onEditClick)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ToDoViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.rvMain.adapter = adapter
        viewModel.todoList.observe(this) {
            adapter.addToList(it)
        }
        viewModel.title.observe(this) {
            title = it
        }
        viewModel.description.observe(this) {
            description = it
        }

        binding.btnSave.setOnClickListener {
            saveList()
        }
    }

    fun saveList() {

        hideKeyboard()

        if (title.isEmpty() || description.isEmpty()) {
            showToast("Please Enter Details")
        }
        if (title.isNotEmpty() && description.isNotEmpty()) {

            val date = DateUtil.getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss")

            if (isEdited) {
                val noteList = ToDoList(title, description, date)
                noteList.id = id
                viewModel.update(noteList)
                isEdited = false
            } else {
                viewModel.insert(ToDoList(title, description, date))
            }

            viewModel.clearText()
            binding.etTitle.clearFocus()
            binding.etDescription.clearFocus()
        }

    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onDeleteCLick(list: ToDoList) {
        viewModel.deleteList(list)

    }

    fun onEditClick(list: ToDoList) {
        binding.etTitle.setText(list.title)
        binding.etDescription.setText(list.description)
        isEdited = true
        id = list.id
    }

}