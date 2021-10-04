package com.precious.androidprogrammingchallenge.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.model.Joke

class JokeAdapter(allJokes: List<Joke>) :
    RecyclerView.Adapter<JokeAdapter.ViewHolder>() {
    var allJokes: List<Joke>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.joke_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstLine.setText(allJokes[position].getJokes())
    }

    override fun getItemCount(): Int {
        return allJokes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstLine: TextView

        init {
            firstLine = itemView.findViewById(R.id.firstline)
        }
    }

    init {
        this.allJokes = allJokes
    }
}
