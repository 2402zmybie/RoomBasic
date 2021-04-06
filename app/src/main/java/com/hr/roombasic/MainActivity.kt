package com.hr.roombasic
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        //数据发生变化界面自动刷新
        wordViewModel.allWordsLive.observe(this,
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
            wordViewModel.insertWords(word,word1)
        }

        buttonClear.setOnClickListener {
            wordViewModel.deletetAllWords()
        }

        buttonUpdate.setOnClickListener {
            val word2 = Word2("hi", "你好啊")
            word2.id = 1
            wordViewModel.updatetWords(word2)
        }

        buttonDelete.setOnClickListener {
            val deleteWord = Word2("","")
            deleteWord.id = 10
            wordViewModel.deletetWords(deleteWord)
        }
    }




}