package com.hr.roombasic

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class WordViewModel(application: Application) : AndroidViewModel(application) {

    var wordDatabase: WordDatabase
    var wordDao: WordDao

    //数据
    var allWordsLive: LiveData<List<Word2>>

    init {
        //单例生成数据库
        wordDatabase = WordDatabase.getInstance(application);
        wordDao = wordDatabase.getWordDao()
        allWordsLive = wordDao.getAllWordsLive()
    }


    //外界操作数据的方法
    fun insertWords(vararg words:Word2) {
        InsertAsyncTask(wordDao).execute(*words)
    }
    fun updatetWords(vararg words:Word2) {
        UpdateAsyncTask(wordDao).execute(*words)
    }
    fun deletetWords(vararg words:Word2) {
        DeleteAsyncTask(wordDao).execute(*words)
    }
    fun deletetAllWords() {
        DeleteAllAsyncTask(wordDao).execute()
    }



    class InsertAsyncTask(private val wordDao:WordDao) : AsyncTask<Word2, Void, Void>() {
        override fun doInBackground(vararg p0: Word2): Void? {
            wordDao.insertWords(*p0)
            return null
        }
    }

    class UpdateAsyncTask(private val wordDao:WordDao) : AsyncTask<Word2, Void, Void>() {
        override fun doInBackground(vararg p0: Word2): Void? {
            wordDao.updateWords(*p0)
            return null
        }
    }


    class DeleteAsyncTask(private val wordDao:WordDao) : AsyncTask<Word2, Void, Void>() {
        override fun doInBackground(vararg p0: Word2): Void? {
            wordDao.deleteWords(*p0)
            return null
        }
    }


    class DeleteAllAsyncTask(private val wordDao:WordDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            wordDao.deleteAllWords()
            return null
        }
    }


}