package com.example.todoapp.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.todoapp.db.ToDoList
import com.example.todoapp.getorAwaitValue
import com.example.todoapp.util.DateUtil
import com.example.todoapp.util.DateUtil.toString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ToDoViewModelTest{

    val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val context = ApplicationProvider.getApplicationContext<Application>()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun testInsertList() = runTest{

        val sut = ToDoViewModel(context)
        val list = ToDoList("First","First Description", DateUtil.getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss"))

     //   sut.insert(list)
        val result = sut.todoList.getorAwaitValue()

        assertEquals(1,result.size)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}