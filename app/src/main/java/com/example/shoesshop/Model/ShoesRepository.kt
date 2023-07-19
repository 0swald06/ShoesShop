package com.example.shoesshop.Model

import android.util.Log
import com.example.shoesshop.Model.Local.Database.ShoesDetailEntity
import com.example.shoesshop.Model.Local.ShoesDao
import com.example.shoesshop.Model.Remote.RetrofitClient

class ShoesRepository(private val shoesDao:ShoesDao) {

    private val networkService = RetrofitClient.retrofitInstance()

    val shoesListLiveData= shoesDao.getAllShoes()

    suspend fun fechShoes(){

        val service = kotlin.runCatching { networkService.fechShoesList() }

        service.onSuccess {

            when (it.code()) {
                in 200..299-> it.body()?.let {

                    shoesDao.insertAllShoes(fromInternetToShoesEntity(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }

    }

    suspend fun fetchShoesDetail(id: String): ShoesDetailEntity? {
        val service = kotlin.runCatching { networkService.fechShoesDetail(id) }
        return service.getOrNull()?.body()?.let {
            val shoesDetailEntity = fromInternetToShoesDetailEntity(it)

            shoesDao.insertAllShoesDetail(shoesDetailEntity)
            shoesDetailEntity
        }
    }



}