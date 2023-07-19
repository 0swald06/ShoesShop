package com.example.shoesshop.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoesshop.Model.Local.Database.ShoesDatabase
import com.example.shoesshop.Model.Local.Database.ShoesDetailEntity
import com.example.shoesshop.Model.Local.Database.ShoesEntity
import com.example.shoesshop.Model.ShoesRepository
import kotlinx.coroutines.launch

class ShoesViewModel(application: Application): AndroidViewModel(application) {


    private val repository:ShoesRepository

    private val shoesDetailLiveData= MutableLiveData<ShoesDetailEntity>()


    init {
        val bd=ShoesDatabase.getDataBase(application)

        val shoesDao=bd.getShoesDao()
        repository= ShoesRepository(shoesDao)
        viewModelScope.launch {
            repository.fechShoes()
        }
    }
    fun getShoesList(): LiveData<List<ShoesEntity>> =repository.shoesListLiveData

    fun getShoesDetail(): LiveData<ShoesDetailEntity> = shoesDetailLiveData

    fun getShoesDetailByIdFromInternet(id:String)= viewModelScope.launch {

        val shoesDetail=repository.fetchShoesDetail(id)
        shoesDetail?.let {
            shoesDetailLiveData.postValue(it)
        }


    }



}