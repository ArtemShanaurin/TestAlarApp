package com.example.testalarapp.database

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class AppDataBase(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("page")
    @Expose
    var page: Int? = null,

    @SerializedName("data")
    @Expose
    var data: ArrayList<DataOfDataBase>? = null)


