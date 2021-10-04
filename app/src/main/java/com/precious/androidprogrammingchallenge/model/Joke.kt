package com.precious.androidprogrammingchallenge.model

class Joke {
    private var id = 0
    private var jokes: String? = null
    private lateinit var categories: Array<String?>

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getJokes(): String? {
        return jokes
    }

    fun setJokes(jokes: String?) {
        this.jokes = jokes
    }

    fun getCategories(): Array<String?>? {
        return categories
    }

    fun setCategories(categories: Array<String?>) {
        this.categories = categories
    }

    fun Joke() {}
    fun Joke(id: Int, joke: String?, categories: Array<String?>) {
        this.id = id
        jokes = joke
        this.categories = categories
    }
}