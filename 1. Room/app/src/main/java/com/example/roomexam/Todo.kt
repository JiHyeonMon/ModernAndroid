package com.example.roomexam

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Todo (
    @ColumnInfo var title: String?
    //id 자동 생성--> 생성자에서 밑으로 따로 빼준다.
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0 //초기값 0
}

//sql database기반이기 때문에 id가 primary key여야 함. Room 사용
