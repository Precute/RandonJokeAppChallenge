package com.precious.androidprogrammingchallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog
import com.precious.androidprogrammingchallenge.utils.validateInput
import com.precious.androidprogrammingchallenge.utils.validateInputForNull

class SearchJokeActivity : AppCompatActivity() {
    lateinit var fullName: EditText
    lateinit var searchBtn: Button
    var noExplicitJoke = false
    lateinit var fullnameToString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.text_input)
        noExplicitJoke = (intent.getBooleanExtra("noExplicitContent",noExplicitJoke ))
        fullName = findViewById(R.id.editTextTextPersonName)
        searchBtn = findViewById(R.id.searchBtn)
        fullnameToString = fullName!!.text.toString()

        searchBtn.setOnClickListener(View.OnClickListener { view ->
            if (checkForInputError()) {
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
    fun checkForInputError(): Boolean {
        if(validateInput(fullnameToString)) {
            fullName!!.requestFocus()
            fullName!!.error = "Enter Fullname"
            return false
        }
        if (validateInputForNull(fullnameToString)) {
            fullName!!.requestFocus()
            fullName!!.error = "This Field is Required"
            return false
        }
        return true
    }

    companion object {
        const val TAG = "myApp"
    }
}

