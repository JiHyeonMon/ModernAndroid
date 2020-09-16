package com.example.roomexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //데이터베이스 생성 - 데이터베이스 객체 생성
        //이렇게 작성시, db가 항상 백그라운드에서 동작하지 않으면 에러가 난다.
        // -> 메인스레드에서 db조작을 해도 괜찮게 allowMainThreadQueries()추가 --> 실무에서는 background 에서 동작하게
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "todo-db").allowMainThreadQueries().build()

        //todoDao를 통해 데이터를 얻어올 수 있다.
        txt_result.text = db.todoDao().getAll().toString()

        
        //todo 추가하고 보여주기
        btn_todo.setOnClickListener {
            db.todoDao().insert(Todo(edit_todo.text.toString(), 1))
            txt_result.text = db.todoDao().getAll().toString()
        }
    }
}