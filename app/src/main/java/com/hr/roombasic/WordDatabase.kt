package com.hr.roombasic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Word2::class], version = 1, exportSchema = false)
abstract class  WordDatabase: RoomDatabase() {

    companion object {
        private var instance:WordDatabase? = null
        fun getInstance(context: Context):WordDatabase {
            if(instance == null) {
                synchronized(WordDatabase::class) {
                    if(instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_database")
                                //强制在主线程执行
//                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
    abstract fun getWordDao():WordDao
}