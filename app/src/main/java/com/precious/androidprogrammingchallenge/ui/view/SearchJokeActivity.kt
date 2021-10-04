package com.precious.androidprogrammingchallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog

class SearchJokeActivity : AppCompatActivity() {
    lateinit var fullName: EditText
    lateinit var searchBtn: Button
    var noExplicitJoke = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.text_input)
        noExplicitJoke = (intent.getBooleanExtra("noExplicitContent",noExplicitJoke ))
        fullName = findViewById(R.id.editTextTextPersonName)
        searchBtn = findViewById(R.id.searchBtn)
        searchBtn.setOnClickListener(View.OnClickListener { view ->
            if (validateInput()) {
                val name = fullName.getText().toString().split(" ").toTypedArray()
                //RandomJokeDialog randomJokeDialog = new RandomJokeDialog(name);
                val randomJokeDialog: RandomJokeDialog =
                    RandomJokeDialog.newInstance(name, noExplicitJoke)
                val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
                randomJokeDialog.show(fragmentManager, "Random Joke")
                fullName.setText("")
            }
        })
    }

    fun validateInput(): Boolean {
        val fullname = fullName!!.text.toString()
        val regex = """^(.*\s+.+)+${'$'}""".toRegex()

        if (fullName!!.length() == 0) {
            fullName!!.requestFocus()
            fullName!!.error = "This field is required"
            return false
        }
        if (!regex.matches(fullname)) {
            fullName!!.requestFocus()
            fullName!!.error = "Enter full name"
            return false
        }
        return true
    }

    companion object {
        const val TAG = "myApp"
    }
}

