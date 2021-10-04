package com.precious.androidprogrammingchallenge.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.precious.androidprogrammingchallenge.R

class RandomJokeAdapter(var randomJoke: List<String>) :
    RecyclerView.Adapter<RandomJokeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.dialog_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.jokeDetail.text = randomJoke[position]
    }

    override fun getItemCount(): Int {
        return randomJoke.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var jokeDetail: TextView

        init {
            jokeDetail = itemView.findViewById(R.id.joke)
        }
    }
}
