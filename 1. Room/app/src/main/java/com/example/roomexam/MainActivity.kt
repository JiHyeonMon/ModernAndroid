package com.example.roomexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //데이터베이스 생성 - 데이터베이스 객체 생성
        //이렇게 작성시, db가 항상 백그라운드에서 동작하지 않으면 에러가 난다.
        // -> 메인스레드에서 db조작을 해도 괜찮게 allowMainThreadQueries()추가 --> 실무에서는 backgroun 에서 동작하게

        //viewmodel로 로직 분리
//        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "todo-db")
////            .allowMainThreadQueries()   앱의 성능이 느려질 수 있다. insert, delete등의 쿼리를 메인스레드에서 사용하면 안된다. (background에서 사용해야함.)
//            .build()


//            //todoDao를 통해 데이터를 얻어올 수 있다.
//            txt_result.text = db.todoDao().getAll().toString()


//            //todo 추가하고 보여주기
//            btn_todo.setOnClickListener {
//                db.todoDao().insert(Todo(edit_todo.text.toString()))
//                txt_result.text = db.todoDao().getAll().toString()
//            }

//        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        val viewmodel = ViewModelProvider(this)[MainViewModel::class.java] //위에랑 완전 똑같은 내용

//        //UI 갱신
//        db.todoDao().getAll().observe(this, Observer {
//            txt_result.text = it.toString()
//        })

        viewmodel.getAll().observe(this, Observer {
            txt_result.text = it.toString()
        })


//        //버튼 클릭시 db에 insert
//        btn_todo.setOnClickListener {
//            db.todoDao().insert(Todo(edit_todo.text.toString()))
//        }

        //비동기 처리를 해줄 것. - 버튼 클릭시 db에 insert
        btn_todo.setOnClickListener {
            //두가지 스레드가 있다. UI스레드, worker스레드 - 보이지 않는 db작업 위해 worker스레드 사용 위해 dispatcher사용 -- background에서 동작한다.
            lifecycleScope.launch(Dispatchers.IO) {
                viewmodel.insert(Todo(edit_todo.text.toString()))  //suspend속성이 붙은 insert는 무조건 corutine scope안에서 실행이 돼야한다.

            }
        }
    }
}