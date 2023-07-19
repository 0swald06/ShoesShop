package com.example.shoesshop.Model.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoesshop.Model.Local.ShoesDao

@Database(entities = [ShoesEntity::class, ShoesDetailEntity::class], version = 1,
exportSchema = false)
abstract class ShoesDatabase:RoomDatabase() {

    abstract fun getShoesDao(): ShoesDao


    companion object{

        private var INSTANCE : ShoesDatabase?=null

        fun getDataBase(context: Context): ShoesDatabase {
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    ShoesDatabase::class.java,"shoes_db")
                    .build()
                INSTANCE =instance
                return instance
            }

        }

    }





}