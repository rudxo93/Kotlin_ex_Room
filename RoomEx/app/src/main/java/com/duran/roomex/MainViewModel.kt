package com.duran.roomex

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.duran.roomex.db.TextDatabase
import com.duran.roomex.entity.TextEntity
import com.duran.roomex.entity.WordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext
    val db = TextDatabase.getDatabase(context)

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("MainViewModel", db.textDao().getAllData().toString())
        Log.d("MainViewModel", db.wordDao().getAllData().toString())
    }

    fun insertData(text : String) = viewModelScope.launch(Dispatchers.IO) {
        db.textDao().insert(TextEntity(0, text))
        db.wordDao().insert(WordEntity(0, text))
    }

    fun removeData() = viewModelScope.launch(Dispatchers.IO) {
        db.textDao().deleteAllData()
        db.wordDao().deleteAllData()
    }



}