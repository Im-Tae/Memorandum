package com.memorandum.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.memorandum.R
import com.memorandum.ui.ShowAndEditMemoActivity
import com.memorandum.util.DataSingleton
import com.memorandum.util.SharedPreferenceManager
import com.memorandum.model.Memo

class MemoAdapter(private val context: Context, private val memoList: ArrayList<Memo>) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

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

        holder.bottomWrapper?.setOnClickListener { deleteMemo(position) }

        holder.swipeLayout?.setOnDoubleClickListener { _: SwipeLayout, _: Boolean ->

            val intent = Intent(context, ShowAndEditMemoActivity::class.java)
            DataSingleton.getInstance()?.title =  memoList[position].title
            DataSingleton.getInstance()?.content = memoList[position].content
            context.startActivity(intent)
        }
    }



    private fun deleteMemo(position: Int) {

        FirebaseFirestore
            .getInstance()
            .collection(SharedPreferenceManager.getUserId(context).toString())
            .document(memoList[position].title.toString() + memoList[position].content.toString())
            .delete()
            .addOnCompleteListener {}

        memoList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, memoList.size)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val titleText = itemView?.findViewById<TextView?>(R.id.titleText)
        val contentText = itemView?.findViewById<TextView?>(R.id.contentText)
        val bottomWrapper = itemView?.findViewById<LinearLayout?>(R.id.bottom_wrapper)
        val swipeLayout = itemView?.findViewById<SwipeLayout?>(R.id.swipeLayout)
    }
}