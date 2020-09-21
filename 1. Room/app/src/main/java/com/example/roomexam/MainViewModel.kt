package com.example.roomexam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "todo-db")
        .build()

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    suspend fun insert(todo: Todo){  //무조건 비동기 처리를 해야할 시, suspend속성을 써준다.
        db.todoDao().insert(todo)
    }
}