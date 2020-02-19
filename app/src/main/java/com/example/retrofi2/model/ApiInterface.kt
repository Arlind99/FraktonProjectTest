package com.example.retrofi2.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getData(
        @Url url: String
    ):Call<UserData>

    @GET
    fun getPersonalData(
        @Url url: String
    ):Call<DetailData>

}