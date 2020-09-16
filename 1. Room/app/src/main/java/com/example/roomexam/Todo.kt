package com.example.roomexam

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @ColumnInfo val title: String?,
    @PrimaryKey(autoGenerate = true) val id: Int  //기본키 자동으로 생성되게
)

//sql database기반이기 때문에 id가 primary key여야 함. Room 사용
