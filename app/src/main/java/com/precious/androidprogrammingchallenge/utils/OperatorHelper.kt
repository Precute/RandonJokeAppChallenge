package com.precious.androidprogrammingchallenge.utils

import android.content.Intent
import android.view.View
import com.precious.androidprogrammingchallenge.ui.view.SearchJokeActivity
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog.Companion.fullname
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog.Companion.noExplicitJoke
import com.precious.androidprogrammingchallenge.ui.view.HomeScreenMainActivity


val title: String
    get() {
        var title = "Random Joke"
        if (fullname != null && fullname.isNotEmpty()) {
            title = "Text Input Joke"
        }
        return title
    }
fun getUrl(url :String): String {
    var urlUpdate = url
    urlUpdate += if (fullname != null && fullname.isNotEmpty()) {
        //http://api.icndb.com/jokes/random?firstName=John&lastName=Doe
        // first name = first element of the array
        // last name = last element of the array
        java.lang.StringBuilder()
            .append("?firstName=").append(fullname!![0]).append("&lastName=")
            .append(fullname!![fullname!!.size - 1]).toString()
    } else {
        setUrl()
    }
    return urlUpdate
}

fun setUrl(): String {
    return if (noExplicitJoke) {
        "?exclude=[explicit]"
    } else ""
}
fun closeDialog(view: View, activity: Class<*>) {
    val myIntent = Intent(view.context, activity)
    view.context.startActivity(myIntent)
}

fun classIntent(): Class<*> {
    return if (RandomJokeDialog.fullname.isEmpty()) {
        HomeScreenMainActivity::class.java
    } else SearchJokeActivity::class.java
}
