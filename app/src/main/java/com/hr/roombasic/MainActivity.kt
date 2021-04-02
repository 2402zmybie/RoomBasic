package com.hr.roombasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var wordDatabase: WordDatabase
    lateinit var wordDao: WordDao
    lateinit var allWordsLive:LiveData<List<Word2>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordDatabase = Room.databaseBuilder(application, WordDatabase::class.java, "word_database")
            .allowMainThreadQueries()
            .build()
        wordDao = wordDatabase.getWordDao()
        allWordsLive = wordDao.getAllWordsLive()

        //数据发生变化界面自动刷新
        allWordsLive.observe(this,
            Observer<List<Word2>> {words ->
                val stringBuilder = StringBuilder()
                words.forEach {
                    stringBuilder.append("${it.id} : ${it.word} = ${it.chineseMeaning} \n")
                }
                textView.text = stringBuilder.toString()
            }
        )


        buttonInsert.setOnClickListener {
            val word = Word2("hello", "你好")
            val word1 = Word2("word", "世界")
            wordDao.insertWords(word, word1)
        }

        buttonClear.setOnClickListener {
            wordDao.deleteAllWords()
        }

        buttonUpdate.setOnClickListener {
            val word2 = Word2("hi", "你好啊")
            word2.id = 1
            wordDao.updateWords(word2)
        }

        buttonDelete.setOnClickListener {
            val deleteWord = Word2("","")
            deleteWord.id = 10
            wordDao.deleteWords(deleteWord)
        }
    }

}