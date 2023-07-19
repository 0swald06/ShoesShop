package com.example.shoesshop.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoesshop.Model.Local.Database.ShoesDetailEntity
import com.example.shoesshop.Model.Local.Database.ShoesEntity


@Dao
interface ShoesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllShoes(listShoes:List <ShoesEntity>)

    @Query("SELECT * FROM shoes_table ORDER BY id ASC")
    fun getAllShoes(): LiveData<List<ShoesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllShoesDetail(shoes: ShoesDetailEntity)

}