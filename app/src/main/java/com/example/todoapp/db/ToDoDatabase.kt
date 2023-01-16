package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoList::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {


    abstract fun todoDao(): ToDoDao

    companion object {
        private var instance: ToDoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ToDoDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, ToDoDatabase::class.java,
                    "todo_database"
                ).build()

            return instance!!

        }
    }
}