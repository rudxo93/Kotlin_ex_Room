package com.duran.roomex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Room

Room에 관한 공식 문서
https://developer.android.com/training/data-storage/room?hl=ko
https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#5
*/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = TextDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            db.textDao().insert(TextEntity(0, "HELLO"))
            Log.d("MAINACTIVITY", db.textDao().getAllData().toString())

        }
    }
}