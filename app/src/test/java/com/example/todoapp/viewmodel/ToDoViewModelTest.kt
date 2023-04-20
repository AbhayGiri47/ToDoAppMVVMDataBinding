package com.example.todoapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.todoapp.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ToDoViewModelTest {

    val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val context = ApplicationProvider.getApplicationContext<Application>()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun testInsertList() = runTest{

        val sut = ToDoViewModel(context)
        sut.deleteAllList()
        val result = sut.todoList

        assertEquals(0,result.value?.size)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}