package com.precious.androidprogrammingchallenge.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.fragment.MainFragment
import java.util.*

fun loadFragment(view: View, fragment: Fragment?) {
    val activity = view.context as AppCompatActivity
    val fragmentManager = activity.supportFragmentManager
    val fragmentTransaction = fragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, (fragment)!!)
    fragmentTransaction.commit()

}

fun setViewFragment(view: View, categoryCardViewPosition: Int, categories: List<String>) {
    if (((categories[categoryCardViewPosition].lowercase(Locale.getDefault())) == "all")) {
        val batch = 10
        loadFragment(
            view, MainFragment(
                view.resources
                    .getString(R.string.base_url) + "random/" + batch + setUrl()
            )
        )
    }
    if (((categories[categoryCardViewPosition].lowercase(Locale.getDefault())) == "no explicit")) {
        val batch = 10
        loadFragment(
            view, MainFragment(
                view.resources
                    .getString(R.string.base_url) + "random/" + batch + "?exclude=[explicit]"
            )
        )
    }
    if (((categories[categoryCardViewPosition].lowercase(Locale.getDefault())) == "nerdy")) {
        val batch = 10
        loadFragment(
            view, MainFragment(
                view.resources
                    .getString(R.string.base_url) + "random/" + batch + "?limitTo=[nerdy]"
            )
        )
    }
    if (((categories[categoryCardViewPosition].lowercase(Locale.getDefault())) == "explicit")) {
        val batch = 10
        loadFragment(
            view, MainFragment(
                view.resources
                    .getString(R.string.base_url) + "random/" + batch + "?limitTo=[explicit]"
            )
        )
    }
}
