package com.hr.roombasic

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var useCardView: Boolean) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var allWords:List<Word2> = ArrayList()
        get()  = field
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //kotlin没有三元表达式
        val itemView = if(useCardView) LayoutInflater.from(parent.context).inflate(R.layout.cell_card,parent,false) else LayoutInflater.from(parent.context).inflate(R.layout.cell_normal,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = allWords.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word2 = allWords.get(position)
        holder.textViewNumber.text = position.toString()
        holder.textViewEnglish.text = word2.word
        holder.textViewChinese.text = word2.chineseMeaning
        holder.itemView.setOnClickListener {
            val uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=${word2.word}")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewNumber:TextView = itemView.findViewById(R.id.textViewNumber);
        var textViewEnglish:TextView = itemView.findViewById(R.id.textViewEnglish);
        var textViewChinese:TextView = itemView.findViewById(R.id.textViewChinese);
    }
}