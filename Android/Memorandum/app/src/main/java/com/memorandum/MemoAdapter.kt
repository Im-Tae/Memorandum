package com.memorandum

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter(private val context: Context, private val memoList: List<Memo>) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.custom_listview, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = memoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = memoList[position]
        holder.titleText?.text = note.title
        holder.contentText?.text = note.content
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val titleText = itemView?.findViewById<TextView?>(R.id.titleText)
        val contentText = itemView?.findViewById<TextView?>(R.id.contentText)
    }
}