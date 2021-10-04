package com.precious.androidprogrammingchallenge.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.utils.classIntent
import com.precious.androidprogrammingchallenge.utils.closeDialog
import com.precious.androidprogrammingchallenge.utils.getUrl
import com.precious.androidprogrammingchallenge.utils.title
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class RandomJokeDialog : AppCompatDialogFragment() {
    lateinit var randomJoke: ArrayList<String>
    var url = "random"
    //lateinit var randomJokeAdapter: RandomJokeAdapter
    lateinit var ok_btn: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(
            requireActivity(), R.style.alertDialog
        )
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_view, null)
        val alertDialog = builder.create()
        ok_btn = view.findViewById(R.id.button)
        ok_btn.setOnClickListener(View.OnClickListener { view: View -> closeDialog(view, classIntent()) })
        setJoke(builder, view)
        return alertDialog
    }

    fun setJoke(builder: AlertDialog.Builder, view: View) {
        val jokeUrl = resources.getString(R.string.base_url) + getUrl(url)
        Log.d(TAG, "the URL is : $jokeUrl")
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, jokeUrl, null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject) {
                    try {
                        builder.setView(view)
                            .setTitle(title)
                            .setMessage(extractJsonObject(response))
                        builder.create().show()
                    } catch (jsonException: JSONException) {
                        jsonException.printStackTrace()
                    }
                }
            },
            { error -> Toast.makeText(context, error.message, Toast.LENGTH_LONG).show() })
        requestQueue.add(jsonObjectRequest)
    }

    @Throws(JSONException::class)
    fun extractJsonObject(response: JSONObject): String {
        val valueJsonObject = response.getJSONObject("value")
        //Log.d(TAG, "Joke for Rams : $valueJsonObject")
        randomJoke = ArrayList()
        val jokeJsonObject1 = valueJsonObject.getString("joke")
        randomJoke.add(jokeJsonObject1)
        //randomJokeAdapter = RandomJokeAdapter(randomJoke)
        //Log.d(TAG, "Joke for Rams : " + randomJoke.size)
        return jokeJsonObject1
    }


    companion object {
        const val TAG = "myApp"
        lateinit var fullname: Array<String>
        var noExplicitJoke: Boolean = false
        fun newInstance(msg: Array<String>, explicitJoke: Boolean): RandomJokeDialog {
            fullname = msg
            noExplicitJoke = explicitJoke
            val fragment = RandomJokeDialog()
            val bundle = Bundle()
            bundle.putString("msg", msg.toString())
            fragment.arguments = bundle
            return fragment
        }
    }
}