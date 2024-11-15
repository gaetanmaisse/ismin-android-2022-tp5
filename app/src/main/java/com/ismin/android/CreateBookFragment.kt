package com.ismin.android

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import java.lang.RuntimeException

class CreateBookFragment : Fragment() {
    private lateinit var isbnInput: EditText
    private lateinit var titleInput: EditText
    private lateinit var authorInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var saveBtn: Button

    private lateinit var listener: BookCreator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_book, container, false)


        isbnInput = rootView.findViewById(R.id.f_create_book_edt_isbn)
        titleInput = rootView.findViewById(R.id.f_create_book_edt_title)
        authorInput = rootView.findViewById(R.id.f_create_book_edt_author)
        dateInput = rootView.findViewById(R.id.f_create_book_edt_date)
        saveBtn = rootView.findViewById(R.id.f_create_book_btn_save)

        saveBtn.setOnClickListener {
            saveBook()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookCreator) {
            listener = context
        } else {
            throw RuntimeException("$context must implements BookCreator")
        }
    }

    private fun saveBook() {
        val isbn = isbnInput.text.toString()
        val title = titleInput.text.toString()
        val author = authorInput.text.toString()
        val date = dateInput.text.toString()

        val book = Book(isbn, title, author, date)
        listener.onBookCreated(book)
    }

}