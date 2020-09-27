Part of Android Development - ISMIN

Course followed by students of Mines St Etienne, ISMIN - M2 Computer Science.

[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# TP3: RecyclerView

## 📝 Goal

The goal is to display a list of book using a `RecyclerView`.

Preparatory work:
- From the previous TP copy:
    - `Book`
    - `Bookshelf`
    - `MainActivity`, `CreateBookActivity` and their layouts
- Add an instance of `Bookshelf` in `MainActivity` to be able to store multiples books created using `CreateBookActivity`

RecyclerView implementation: 
- Create a `row_book` XML file in `res/layout`. In this file, create a layout to display content of a book (title, author, date)
- Create a `BookViewHolder` class and implement it as it's done in the course
- Create a `BookAdapter` class and implement it as it's done in the course (the constructor should deal with a `ArrayList<Book>`)
- Add a `<recycler-view>` in the layout of the `MainActivity`
- Link the RecyclerView and the BookAdapter in the `MainActivity`

⚠️ The list must be updated each time a book is created.

About UX:
- Add a menu in the Toolbar with a button to clear the list of books`
- Customize app theme
- Use a `FloatingActionButton` instead of a simple `Button` to navigate to `CreateBookActivity

## 🚀 Getting Started

 - Start Android Studio
 - Select `Open an existing Android Studio project` and pick this directory

That's it! You can code!

## 🛰 Extra

- Display the books in a grid instead of a list
- Add a button on each book of the list to be able to delete it

## 🔑 Solution

An implementation of the TP is available on `solution` branch. To switch to the solution just do:

```
# Commit or revert your local changes
# git add . && git commit -m "YOUR_MSG" 

# Update your repository
git pull

# Switch to `solution` branch
git checkout solution
```