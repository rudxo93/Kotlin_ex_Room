package com.duran.roomex

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.duran.roomex.db.TextDatabase
import com.duran.roomex.entity.TextEntity
import com.duran.roomex.entity.WordEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Room

Room에 관한 공식 문서
https://developer.android.com/training/data-storage/room?hl=ko
https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#5

Dispatchers(사전적 의미 : 디스패처, 배차원, 발차원, 조차원 / 스레드를 어떻게 배차할것인가)
Dispatchers에 관한 공식 문서
https://developer.android.com/kotlin/coroutines/coroutines-adv?hl=ko
*/

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData()

        val db = TextDatabase.getDatabase(this)

        val inputArea = findViewById<EditText>(R.id.textInputArea)
        val insertBtn = findViewById<Button>(R.id.insert)
        val getAllBtn = findViewById<Button>(R.id.getData)
        val deleteBtn = findViewById<Button>(R.id.delete)

        insertBtn.setOnClickListener {
            viewModel.insertData(inputArea.text.toString())
            inputArea.setText("")
        }

        getAllBtn.setOnClickListener {
            viewModel.getData()
        }

        deleteBtn.setOnClickListener {
            viewModel.removeData()
        }






    }
}