package com.moviedemo_retrofitrxjava.interfaces

import com.moviedemo_retrofitrxjava.API_JSON
import com.moviedemo_retrofitrxjava.models.MovieList
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Costi on 10/17/2018.
 */
interface IApiMoview {

    @GET(API_JSON)
    fun getMovies(): Observable<MovieList>
}