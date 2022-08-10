package com.duran.roomex

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.duran.roomex.db.TextDatabase
import com.duran.roomex.entity.TextEntity
import com.duran.roomex.entity.WordEntity
import com.duran.roomex.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>().applicationContext
    val db = TextDatabase.getDatabase(context)

    private var _textList = MutableLiveData<List<TextEntity>>()
    val textList : LiveData<List<TextEntity>>
        get() = _textList

    private var _wordList = MutableLiveData<List<WordEntity>>()
    val wordList : LiveData<List<WordEntity>>
        get() = _wordList

    val repository = Repository(context)

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
//        Log.d("MainViewModel", db.textDao().getAllData().toString())
//        Log.d("MainViewModel", db.wordDao().getAllData().toString())
        _textList.postValue(repository.getTextList())
        _wordList.postValue(repository.getWordList())
    }

    fun insertData(text : String) = viewModelScope.launch(Dispatchers.IO) {
//        db.textDao().insert(TextEntity(0, text))
//        db.wordDao().insert(WordEntity(0, text))
        repository.insertTextData(text)
        repository.insertWordData(text)
    }

    fun removeData() = viewModelScope.launch(Dispatchers.IO) {
//        db.textDao().deleteAllData()
//        db.wordDao().deleteAllData()
        repository.deleteTextData()
        repository.deleteWordData()
    }

}