package com.ismin.android


class Bookshelf {
    private val bookshelf: MutableMap<String, Book> = mutableMapOf()

    fun addBook(book: Book) {
        this.bookshelf[book.isbn] = book
    }

    fun getBook(isbn: String): Book {
        return bookshelf[isbn] ?: throw Exception("Book not found")
    }

    fun getBooksOf(author: String): List<Book> {
        // Map -> Map -> List
       return this.bookshelf.filterValues { it.author == author }.values.sortedBy { it.title }
    }

    fun getAllBooks(): List<Book> =
        this.bookshelf.values.sortedBy { it.title }

    fun getTotalNumberOfBooks(): Int = this.bookshelf.size

}