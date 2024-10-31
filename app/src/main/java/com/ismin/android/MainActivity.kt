package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnCreateBook: Button
    private lateinit var txvBook: TextView

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val book = result.data?.getSerializableExtra(CREATED_BOOK) as Book
            txvBook.text = book.toString()
            Toast.makeText(this, "Created book:$book", Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txvBook = findViewById(R.id.a_main_txv_book)

        btnCreateBook = findViewById(R.id.a_main_btn_create_book)
        btnCreateBook.setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }
    }
}