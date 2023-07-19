package com.example.shoesshop.Model.Local.Database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shoes_details_table")
data class ShoesDetailEntity (

    @PrimaryKey
    @NonNull
    val id:String,
    val nombre:String,
    val origen:String,
    val image:String,
    val marca:String,
    val numero:String,
    val precio:String,
    val entrega:String


)