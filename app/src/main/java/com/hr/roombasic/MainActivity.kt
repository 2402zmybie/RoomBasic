package com.hr.roombasic
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(WordViewModel::class.java)

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
            word2.id = 30
            wordViewModel.updatetWords(word2)
        }

        buttonDelete.setOnClickListener {
            val deleteWord = Word2("","")
            deleteWord.id = 10
            wordViewModel.deletetWords(deleteWord)
        }
    }




}