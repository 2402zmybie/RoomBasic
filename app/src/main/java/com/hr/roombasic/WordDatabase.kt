package com.hr.roombasic

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Word2::class], version = 1, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {

    abstract fun getWordDao():WordDao
}