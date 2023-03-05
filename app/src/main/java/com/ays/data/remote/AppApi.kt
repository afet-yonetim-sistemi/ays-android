package com.ays.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*

interface AppApi {

    @POST("api/v2/sendlcoation")
    @Headers("Authorization: ")
    suspend fun forgetPasswordEmailSend(
        @Body body: JsonObject
    ): Response<String>

}