package com.example.testalarapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemouteData (
    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("code")
    @Expose
    val code: String? = null
)