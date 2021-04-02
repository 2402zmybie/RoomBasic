package com.hr.roombasic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Word2{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    @ColumnInfo(name = "english_word")
    var word:String? = null
    @ColumnInfo(name = "chinese_meaning")
    var chineseMeaning:String?=null

    constructor(word: String?, chineseMeaning: String?) {
        this.word = word
        this.chineseMeaning = chineseMeaning
    }



}