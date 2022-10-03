package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieListAdapter(private val movies: List<MovieItem>,
                       private val mListener: MovieListFragment

) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){
    inner class ViewHolder(val itemView : View) : RecyclerView.ViewHolder(itemView){
        var titleTextView = itemView.findViewById<TextView>(R.id.title) as TextView
        var movieImgView = itemView.findViewById<ImageView>(R.id.movieImg) as ImageView
        var overview = itemView.findViewById<TextView>(R.id.overview) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]

        holder.titleTextView.text = item.movieTitle
        holder.overview.text = item.movieOverview

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}