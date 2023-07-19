package com.example.shoesshop.Model.Remote

import com.example.shoesshop.Model.Remote.FromInternet.Shoes
import com.example.shoesshop.Model.Remote.FromInternet.ShoesDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoesApi {

    @GET("shoes")
    suspend fun fechShoesList(): Response<List<Shoes>>


    @GET("shoes/{id}")
    suspend fun fechShoesDetail(@Path("id")id:String): Response<ShoesDetail>

}