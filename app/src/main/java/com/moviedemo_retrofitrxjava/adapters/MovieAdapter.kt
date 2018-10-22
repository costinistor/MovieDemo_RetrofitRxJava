package com.moviedemo_retrofitrxjava.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.moviedemo_retrofitrxjava.R
import com.moviedemo_retrofitrxjava.models.Movie
import com.squareup.picasso.Picasso


class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    val movies = arrayListOf<Movie>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindModel(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_movie, parent, false))

    }


    fun setMovies(data: List<Movie>) {
        movies.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val movieTitle = itemView.findViewById<TextView>(R.id.tvMovieTitle)
        private val movieGenre = itemView.findViewById<TextView>(R.id.tvMovieGenre)
        private val movieYear = itemView.findViewById<TextView>(R.id.tvMovieYear)
        private val movieImage = itemView.findViewById<ImageView>(R.id.imgMovie)

        fun bindModel(movie: Movie){
            movieTitle.text = movie.title
            movieGenre.text = movie.genre
            movieYear.text = movie.year
            Picasso.get().load(movie.poster).into(movieImage)
        }
    }
}