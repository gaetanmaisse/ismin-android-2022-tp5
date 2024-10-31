package com.ismin.android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

val CREATED_BOOK = "CREATED_BOOK"

class CreateBookActivity : AppCompatActivity() {
    private lateinit var isbnInput: EditText
    private lateinit var titleInput: EditText
    private lateinit var authorInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        isbnInput = findViewById(R.id.a_create_book_edt_isbn)
        titleInput = findViewById(R.id.a_create_book_edt_title)
        authorInput = findViewById(R.id.a_create_book_edt_author)
        dateInput = findViewById(R.id.a_create_book_edt_date)
        saveBtn = findViewById(R.id.a_create_book_btn_save)

        saveBtn.setOnClickListener {
            saveBook()
        }
    }

    private fun saveBook() {
        val isbn = isbnInput.text.toString()
        val title = titleInput.text.toString()
        val author = authorInput.text.toString()
        val date = dateInput.text.toString()

        val book = Book(isbn, title, author, date)
        intent.putExtra(CREATED_BOOK, book)
        setResult(RESULT_OK, intent)
        finish()
    }
}