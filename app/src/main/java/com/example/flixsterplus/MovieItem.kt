package com.example.flixsterplus

import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject


class MovieItem () {

    @JvmField
    @SerializedName("original_title")
    var movieTitle: String? = null

    @JvmField
    @SerializedName("overview")
    var movieOverview: String? = null

    //TODO bookImageUrl
    @JvmField
    @SerializedName("poster_path")
    var movieImgPath : String? = null

}