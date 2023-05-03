package com.example.todoapp.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.todoapp.getorAwaitValue
import com.example.todoapp.util.DateUtil
import com.example.todoapp.util.DateUtil.toString
import kotlinx.coroutines.runBlocking
import org.junit.*


class ToDoDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // get reference to the ToDoDatabase and ToDoDao class
    private lateinit var db: ToDoDatabase
    private lateinit var dao: ToDoDao

    @Before
    fun setUp() {

        // get context -- since this is an instrumental test it requires
        // context from the running application
        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, ToDoDatabase::class.java).allowMainThreadQueries().build()
        dao = db.todoDao()
    }

    @Test
    fun testDB_insertToDoList_expectedSingleToDoList()= runBlocking{

        val list = ToDoList("First","First Description", DateUtil.getCurrentDateTime().toString("yyyy/MM/dd HH:mm:ss"))

        dao.insert(list)

        val result = dao.getToDolist().getorAwaitValue()

        Assert.assertEquals(1,result.size)
        Assert.assertEquals("First",result[0].title)

    }

    @After
    fun tearDown() {
        db.close()
    }
}