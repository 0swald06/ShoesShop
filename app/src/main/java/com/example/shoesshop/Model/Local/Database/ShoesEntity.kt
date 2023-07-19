package com.example.shoesshop.Model.Local.Database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shoes_table")
data class ShoesEntity (

    @PrimaryKey
    @NonNull
    val id:String,
    val nombre:String,
    val origen:String,
    val image:String,
    val marca:String,
    val numero:String



        )