package com.example.testalarapp.database

import android.widget.TextView
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class DataOfDataBase (
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("country")
    @Expose
    val country: String? = null,

    @SerializedName("lat")
    @Expose
    val lat: Double? = null,

    @SerializedName("lon")
    @Expose
    val lon: Double? = null)


