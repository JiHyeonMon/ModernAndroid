package com.example.roomexam

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao { //Data Access Object - Todo에 대한 어떤 동작을 하는지 작성

    //TODO테이블의 전체 데이터를 관측해야함.
    @Query("SELECT * FROM TODO")
    fun getAll(): LiveData<List<Todo>> //--->getAll()은 이제 관찰가능한 객체가 된다.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)

}