package com.ismin.android

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    var txvIsbn = rootView.findViewById<TextView>(R.id.r_book_txv_isbn)
    var txvTitle = rootView.findViewById<TextView>(R.id.r_book_txv_title)
    var txvAuthor = rootView.findViewById<TextView>(R.id.r_book_txv_author)
    var txvDate = rootView.findViewById<TextView>(R.id.r_book_txv_date)
}