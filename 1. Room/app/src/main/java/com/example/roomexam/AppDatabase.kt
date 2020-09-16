package com.example.roomexam

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase ()//데이터베이스 클래스가 필요
{
    abstract fun todoDao(): TodoDao //위 객체가 제공하는 Data Access Object Dao가 필요
}

//AppDatabase를 생성한 뒤에, todoDao를 통해서 TodoDao 이용 가능