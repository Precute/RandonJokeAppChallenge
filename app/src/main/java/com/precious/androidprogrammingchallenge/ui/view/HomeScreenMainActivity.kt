package com.precious.androidprogrammingchallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.adapter.HomeAdapter
import java.util.ArrayList

class HomeScreenMainActivity : AppCompatActivity() {
    private lateinit var homeListView: RecyclerView
    lateinit var homeBottons: ArrayList<String>
    var homeAdapter: HomeAdapter? = null
    lateinit var noExplicitJoke: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen_main)
        homeBottons = ArrayList()
        //populate Category recycle view
        homeBottons.add(getString(R.string.home_btn_random_joke))
        homeBottons.add(getString(R.string.home_btn_text_input))
        homeBottons.add(getString(R.string.home_btn_never_ending_jokes))
        noExplicitJoke = findViewById(R.id.checkBox)
        homeListView = findViewById(R.id.homebtns)
        homeAdapter = HomeAdapter(homeBottons, noExplicitJoke.isChecked)
        homeListView.setLayoutManager(LinearLayoutManager(this))
        homeListView.setAdapter(homeAdapter)

    }
}