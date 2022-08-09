package com.duran.roomex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.duran.roomex.dao.TextDao
import com.duran.roomex.dao.WordDao
import com.duran.roomex.entity.TextEntity
import com.duran.roomex.entity.WordEntity

@Database(entities = [TextEntity::class, WordEntity::class], version = 2)
abstract class TextDatabase : RoomDatabase() {

    abstract fun textDao() : TextDao
    abstract fun wordDao() : WordDao

    companion object {
        @Volatile
        private var INSTANCE : TextDatabase? = null

        fun getDatabase(
            context : Context
        ) : TextDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TextDatabase::class.java,
                    "text_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}