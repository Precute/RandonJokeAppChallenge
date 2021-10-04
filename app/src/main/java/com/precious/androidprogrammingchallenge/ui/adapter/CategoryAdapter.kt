package com.precious.androidprogrammingchallenge.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.precious.androidprogrammingchallenge.R
import com.precious.androidprogrammingchallenge.utils.setViewFragment

class CategoryAdapter     //constructor to receive the list of categories
    (var categories: List<String>, var noExplicitJoke: Boolean) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var categoryCardViewPosition = 0
    var noExplicit = "?exclude=[explicit]"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categories.get(position)
        if (categoryCardViewPosition == position) {
            //set Card BackGround to blue
            holder.categoryCardView.setCardBackgroundColor(
                ContextCompat
                    .getColor(holder.categoryName.context, R.color.coop_blue)
            )
            holder.categoryName.setTextColor(Color.WHITE)
        } else { //set to white
            holder.categoryCardView.setCardBackgroundColor(Color.WHITE)
            holder.categoryName.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var categoryName: TextView
        var categoryCardView: CardView
        override fun onClick(view: View) {
            notifyItemChanged(categoryCardViewPosition)
            categoryCardViewPosition = adapterPosition
            notifyItemChanged(categoryCardViewPosition)
            setViewFragment(view,categoryCardViewPosition, categories )

            //http://api.icndb.com/jokes/random/3/
        }

        init {
            categoryName = itemView.findViewById(R.id.categoryName)
            categoryCardView = itemView.findViewById(R.id.categoryCardView)
            itemView.setOnClickListener(this)
        }
    }

    fun setView(
        holder: CategoryAdapter.ViewHolder,
        position: Int
    ) {
        holder.categoryName.setText(categories[position])
        if (categoryCardViewPosition == position) {
            //set Card BackGround to blue
            holder.categoryCardView.setCardBackgroundColor(
                ContextCompat
                    .getColor(holder.categoryName.getContext(), R.color.coop_blue)
            )
            holder.categoryName.setTextColor(Color.WHITE)
        } else { //set to white
            holder.categoryCardView.setCardBackgroundColor(Color.WHITE)
            holder.categoryName.setTextColor(Color.BLACK)
        }
    }

}
