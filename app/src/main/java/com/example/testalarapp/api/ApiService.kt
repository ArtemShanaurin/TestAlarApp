package com.example.testalarapp.api

import com.example.testalarapp.RemouteData
import com.example.testalarapp.database.AppDataBase
import com.example.testalarapp.database.DataOfDataBase
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("auth.cgi/")
    fun getInfo(
        @Query(QUERY_PARAM_USERNAME) username: String,
        @Query(QUERY_PARAM_PASSWORD) password: String
    ): Single<RemouteData>

    @GET("data.cgi/")
    fun getDataBase(
        @Query(QUERY_PARAM_CODE) code: String,
        @Query(QUERY_PARAM_PAGE_NUMBER) p: Int
    ): Single<AppDataBase>


    companion object{
        private const val QUERY_PARAM_USERNAME = "username"
        private const val QUERY_PARAM_PASSWORD = "password"
        private const val QUERY_PARAM_CODE = "code"
        private const val QUERY_PARAM_PAGE_NUMBER = "p"
    }
}