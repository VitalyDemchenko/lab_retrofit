package com.example.lect_retrofit.filesretro


import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/users?page=2")
    suspend fun getAllUsers(): Response<ResponseListUsers>

}