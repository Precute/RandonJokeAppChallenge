package com.precious.androidprogrammingchallenge.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.ui.dialog.RandomJokeDialog
import com.precious.androidprogrammingchallenge.ui.view.NeverEndingJokeActivity
import com.precious.androidprogrammingchallenge.ui.view.SearchJokeActivity

class HomeAdapter(var homeBottons: List<String>, var noExplicitJoke: Boolean) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var btnPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.homeName.text = homeBottons[position]
        if (btnPosition == position) {
            //set Card BackGround to blue
            holder.homeCardView.setCardBackgroundColor(
                ContextCompat
                    .getColor(holder.homeName.context, R.color.coop_blue)
            )
            holder.homeName.setTextColor(Color.WHITE)
        } else { //set to white
            holder.homeCardView.setCardBackgroundColor(Color.WHITE)
            holder.homeName.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return homeBottons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var homeName: TextView
        var homeCardView: CardView
        override fun onClick(view: View) {
            notifyItemChanged(btnPosition)
            btnPosition = adapterPosition
            notifyItemChanged(btnPosition)
            if (homeBottons[btnPosition] == "Random Joke") {

                val fullname : Array<String> = emptyArray()
                val popup: RandomJokeDialog = RandomJokeDialog.newInstance(fullname, noExplicitJoke)
                val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
                popup.show(fragmentManager, "Random Joke")
            }
            if (homeBottons[btnPosition] == "Text Input") {
                notifyItemChanged(btnPosition)
                val myIntent = Intent(view.context, SearchJokeActivity::class.java)
                myIntent.putExtra("noExplicitContent", noExplicitJoke)
                view.context.startActivity(myIntent)
            }
            if (homeBottons[btnPosition] == "Never-ending Jokes") {
                notifyItemChanged(btnPosition)
                val myIntent = Intent(view.context, NeverEndingJokeActivity::class.java)
                myIntent.putExtra("noExplicitContent", noExplicitJoke)
                view.context.startActivity(myIntent)
            }
        }

        init {
            homeName = itemView.findViewById(R.id.categoryName)
            homeCardView = itemView.findViewById(R.id.categoryCardView)
            itemView.setOnClickListener(this)
        }
    }
}
