package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private var books: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_book, parent, false)
        return BookViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.txvIsbn.text = "ISBN: ${book.isbn}"
        holder.txvTitle.text = book.title
        holder.txvAuthor.text = book.author
        holder.txvDate.text = book.date
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateBooks(allBooks: List<Book>) {
        books = allBooks
        notifyDataSetChanged()
    }
}