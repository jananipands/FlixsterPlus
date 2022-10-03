package com.example.flixsterplus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

import org.json.JSONObject

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MovieListFragment : Fragment(){

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_item_layout, container, false)
        val recyclerView = view.findViewById<View>(R.id.movies_rv) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(recyclerView: RecyclerView) {
        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
        params["location"] = "en-US"
        params["region"] = "US"

        client["https://api.themoviedb.org/3/movie/now_playing", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {

                val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                val moviesRawJson : String = resultsJSON.toString()


                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<MovieItem>>(){}.type

                val models : List<MovieItem> = gson.fromJson(moviesRawJson, arrayMovieType)
                recyclerView.adapter = MovieListAdapter(models, this@MovieListFragment)

                // called when response HTTP status is "200 OK"
                Log.d("MovieListFragment", "Response sucessful!")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                Log.e("MovieListFragment", "!!! Response Incorrect !!!")
            }
        }]

    }


}