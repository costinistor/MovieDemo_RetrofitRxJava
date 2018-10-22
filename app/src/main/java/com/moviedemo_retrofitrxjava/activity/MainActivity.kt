package com.moviedemo_retrofitrxjava.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.moviedemo_retrofitrxjava.BASE_URL
import com.moviedemo_retrofitrxjava.interfaces.IApiMoview
import com.moviedemo_retrofitrxjava.R
import com.moviedemo_retrofitrxjava.adapters.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var movieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter()
        movie_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = movieAdapter

        loadDataWithRetrofit()
    }

    fun loadDataWithRetrofit(){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val apiMovies = retrofit.create(IApiMoview::class.java)

        apiMovies.getMovies()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieAdapter.setMovies(it.data)
                },{
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })
    }
}
