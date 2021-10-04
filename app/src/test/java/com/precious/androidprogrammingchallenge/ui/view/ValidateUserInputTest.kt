package com.precious.androidprogrammingchallenge.ui.view

import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog.Companion.fullname
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog.Companion.noExplicitJoke
import com.precious.androidprogrammingchallenge.utils.getUrl
import com.precious.androidprogrammingchallenge.utils.setUrl
import com.precious.androidprogrammingchallenge.utils.title
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidateUserInputTest {


    @Test
    fun titlieWhenFullnemeIsEmpty(){
        fullname = emptyArray()
        val result = title
        assertEquals(result, "Random Joke")
    }

    @Test
    fun titlieWhenFullnemeNoyEmpty(){
        fullname = arrayOf("precious", "igbinosun")
        val result = title
        assertEquals(result, "Text Input Joke")
    }

    @Test
    fun checkExplicitJokeAllowed(){
        noExplicitJoke = true;
        val result = setUrl()
        assertEquals(result, "?exclude=[explicit]")
    }

    @Test
    fun getUrlSetUp(){
        var url = "random"
        fullname = arrayOf("precious", "samdra", "igbinosun")
        val result = getUrl(url)
        assertEquals(result, "random?firstName=precious&lastName=igbinosun")
    }

    @Test
    fun getUrlSetUpWithEmptyFullnmae(){
        noExplicitJoke = true;
        var url = "random"
        fullname = emptyArray()
        val result = getUrl(url)
        assertEquals(result, "random?exclude=[explicit]")
    }

}