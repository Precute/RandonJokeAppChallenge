package com.precious.androidprogrammingchallenge.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.adapter.JokeAdapter
import com.precious.androidprogrammingchallenge.model.Joke
import org.json.JSONException
import java.util.ArrayList

class MainFragment(var jokeUrl: String) : Fragment() {
    lateinit var jokeListRecyclerView: RecyclerView
    lateinit var nestedScrollView: NestedScrollView
    lateinit var progressBar: ProgressBar
    lateinit var jokeAdapter: JokeAdapter
    var allJokes: MutableList<Joke>
    var pages = 1
    var limits = 20
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        jokeListRecyclerView = view.findViewById(R.id.jokeList)
        progressBar = view.findViewById(R.id.progressBar)
        nestedScrollView = view.findViewById(R.id.scrowFragment)
        jokeAdapter = JokeAdapter(allJokes)
        jokeListRecyclerView.setLayoutManager(LinearLayoutManager(view.context))
        jokeListRecyclerView.setAdapter(jokeAdapter)
        getJokes(jokeUrl)
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                //when reach last Item position increase the page size
                pages++
                progressBar.setVisibility(View.VISIBLE)
                getJokes(jokeUrl)
            }
        })
        return view
    }

    fun getJokes(jokeUrl: String?) {
        //extract json using volley
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, jokeUrl, null,
            { response ->
                Log.d(TAG, "onResponse : $response")
                //progressBar.setVisibility(View.GONE);
                progressBar!!.visibility = View.VISIBLE
                try {
                    val jokeJsonArray = response.getJSONArray("value")
                    val numbersOfJokes = jokeJsonArray.length()
                    Log.d(TAG, "onResponse : $numbersOfJokes")
                    for (index in 0 until numbersOfJokes) {
                        val joke = jokeJsonArray.optJSONObject(index)
                        //Log.d(TAG, "Joke : " + joke.getString("joke"));
                        //Log.d(TAG, "Category : " + joke.getString("categories"));
                        val jokeInstance = Joke()
                        jokeInstance.setJokes(joke.getString("joke"))
                        allJokes.add(jokeInstance)
                        jokeAdapter = JokeAdapter(allJokes)
                        jokeListRecyclerView!!.adapter = jokeAdapter
                        jokeAdapter.notifyDataSetChanged()
                    }
                } catch (jsonException: JSONException) {
                    jsonException.printStackTrace()
                }
            }
        ) { error -> Toast.makeText(context, error.message, Toast.LENGTH_LONG).show() }
        requestQueue.add(jsonObjectRequest)
    }

    companion object {
        const val TAG = "myApp"
    }

    init {
        allJokes = ArrayList<Joke>()
    }
}
