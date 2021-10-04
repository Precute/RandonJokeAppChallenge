package com.precious.androidprogrammingchallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.fragment.MainFragment
import com.precious.androidprogrammingchallenge.ui.main.adapter.CategoryAdapter
import java.lang.Boolean
import java.util.ArrayList

class NeverEndingJokeActivity : AppCompatActivity() {
    lateinit var categoryList: RecyclerView
    lateinit var allCategories: ArrayList<String>
    lateinit var categoryAdapter: CategoryAdapter
    var noExplicitJoke = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        allCategories = ArrayList()
        //populate Category recycle view
        allCategories.add("All")
        allCategories.add("No Explicit")
        allCategories.add("Nerdy")
        allCategories.add("Explicit")
        categoryList = findViewById(R.id.homebtns)
        noExplicitJoke = (intent.getBooleanExtra("noExplicitContent",noExplicitJoke ))
        categoryAdapter = CategoryAdapter(allCategories, noExplicitJoke)
        categoryList.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL, false
            )
        )
        categoryList.setAdapter(categoryAdapter)

        //populate the fragment for each Category
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                MainFragment(resources.getString(R.string.base_url) + "random/" + 10 + setUrl())
            )
        fragmentTransaction.commit()
    }

    fun setUrl(): String {
        return if (noExplicitJoke) {
            "?exclude=[explicit]"
        } else ""
    }
}