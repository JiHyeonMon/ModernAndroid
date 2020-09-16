package com.example.roomexam

import androidx.room.*

@Dao
interface TodoDao { //Data Access Object - Todo에 대한 어떤 동작을 하는지 작성
    @Query("SELECT * FROM TODO")
    fun getAll(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

}