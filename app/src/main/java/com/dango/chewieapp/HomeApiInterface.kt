package com.dango.chewieapp

import models.CommandsApiModelItem
import retrofit2.Call
import retrofit2.http.GET

interface HomeApiInterface {
    @GET("api/auth/twitch")
    fun getLoginInformation(): Call<List<CommandsApiModelItem>>
}