package com.duran.roomex.repository

import android.content.Context
import com.duran.roomex.db.TextDatabase
import com.duran.roomex.entity.TextEntity
import com.duran.roomex.entity.WordEntity

class Repository(context : Context) {

    val db = TextDatabase.getDatabase(context)

    fun getTextList() = db.textDao().getAllData()

    fun getWordList() = db.wordDao().getAllData()

    fun insertTextData(text: String) = db.textDao().insert(TextEntity(0, text))

    fun insertWordData(text: String) = db.wordDao().insert(WordEntity(0, text))

    fun deleteTextData() = db.textDao().deleteAllData()

    fun deleteWordData() = db.wordDao().deleteAllData()

}