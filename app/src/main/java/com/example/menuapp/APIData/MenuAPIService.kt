package com.example.menuapp.APIData

import com.example.menuapp.Model.MenuModel
import retrofit2.http.GET

interface MenuAPIService {
    @GET("") // API-link (json)
    suspend fun requestData(): retrofit2.Response<MenuModel>
}