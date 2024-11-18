package com.ismin.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://bookshelf-gme.cleverapps.io"

class MainActivity : AppCompatActivity(), BookCreator {

    private val bookshelf = Bookshelf()

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val bookService = retrofit.create(BookService::class.java)


    private val btnCreateBook: FloatingActionButton by lazy {
        findViewById(R.id.a_main_btn_create_book)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookService.getAllBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(
                call: Call<List<Book>>, response: Response<List<Book>>
            ) {
                val allBooks: List<Book>? = response.body()
                allBooks?.forEach { bookshelf.addBook(it) }
                displayBookListFragment()
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(baseContext, "Something wrong happened", Toast.LENGTH_SHORT).show()
            }
        })


        btnCreateBook.setOnClickListener {
            displayCreateBookFragment()
        }
    }

    private fun displayBookListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())
        fragmentTransaction.replace(R.id.a_main_lyt_container, bookListFragment)
        fragmentTransaction.commit()
        btnCreateBook.visibility = View.VISIBLE
    }

    private fun displayCreateBookFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val createBookFragment = CreateBookFragment()
        fragmentTransaction.replace(R.id.a_main_lyt_container, createBookFragment)
        fragmentTransaction.commit()
        btnCreateBook.visibility = View.GONE
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                bookshelf.clear()
                displayBookListFragment()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBookCreated(book: Book) {
        bookService.createBook(book)
            .enqueue {
                onResponse = {
                    val bookFromServer: Book? = it.body()
                    bookshelf.addBook(bookFromServer!!)
                    displayBookListFragment()
                }
                onFailure = {
                    Toast.makeText(this@MainActivity, it?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}