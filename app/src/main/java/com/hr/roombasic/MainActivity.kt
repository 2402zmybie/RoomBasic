package com.hr.roombasic
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var wordViewModel: WordViewModel
    lateinit var myAdapter: MyAdapter
    lateinit var myAdapterCard: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(WordViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = MyAdapter(false)
        myAdapterCard = MyAdapter(true)
        recyclerView.adapter = myAdapter

        switch1.setOnCheckedChangeListener { compoundButton, b ->
            if(b) {
                recyclerView.adapter = myAdapterCard
            }else {
                recyclerView.adapter = myAdapter
            }
        }

        //数据发生变化界面自动刷新
        wordViewModel.allWordsLive.observe(this,
            Observer<List<Word2>> {words ->
//                val stringBuilder = StringBuilder()
//                words.forEach {
//                    stringBuilder.append("${it.id} : ${it.word} = ${it.chineseMeaning} \n")
//                }
//                textView.text = stringBuilder.toString()
                myAdapter.allWords = words
                myAdapter.notifyDataSetChanged()

                myAdapterCard.allWords = words
                myAdapterCard.notifyDataSetChanged()
            }
        )


        buttonInsert.setOnClickListener {
            val english = arrayOf(
                "Hello",
                "Word",
                "Android",
                "Google",
                "Studio",
                "Project",
                "Database",
                "Recycler",
                "View",
                "String",
                "Value",
                "Integer"
            )
            val chinese = arrayOf(
                "你好",
                "世界",
                "安卓",
                "谷歌",
                "工作室",
                "项目",
                "数据库",
                "回收站",
                "视图",
                "字符串",
                "值",
                "整形"
            )
            //循环
            for(i in 0 until english.size) {
                wordViewModel.insertWords(Word2(english[i], chinese[i]))
            }
        }

        buttonClear.setOnClickListener {
            wordViewModel.deletetAllWords()
        }
    }




}