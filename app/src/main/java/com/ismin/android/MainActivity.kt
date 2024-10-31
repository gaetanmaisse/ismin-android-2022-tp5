package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val bookshelf = Bookshelf()
    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val book = it.data?.getSerializableExtra(CREATED_BOOK) as Book
                bookshelf.addBook(book)
                bookAdapter.updateBooks(bookshelf.getAllBooks())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        recyclerView = findViewById(R.id.a_main_rcv_books)
        bookAdapter = BookAdapter(bookshelf.getAllBooks())
        recyclerView.adapter = bookAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        findViewById<Button>(R.id.a_main_btn_create_book).setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }
    }

    private fun initData() {
        bookshelf.addBook(
            Book(
                "978-2253004226",
                "Le meilleur des mondes",
                "Aldous Huxley",
                "1932-01-01"
            )
        )
        bookshelf.addBook(Book("978-2070413119", "1984", "George Orwell", "1949-06-08"))
        bookshelf.addBook(Book("978-2070368229", "Fahrenheit 451", "Ray Bradbury", "1953-10-01"))
    }
}