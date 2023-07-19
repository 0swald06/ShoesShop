package com.example.shoesshop.Model

import com.example.shoesshop.Model.Local.Database.ShoesDetailEntity
import com.example.shoesshop.Model.Local.Database.ShoesEntity
import com.example.shoesshop.Model.Remote.FromInternet.Shoes
import com.example.shoesshop.Model.Remote.FromInternet.ShoesDetail

fun fromInternetToShoesEntity(shoesList:List<Shoes>):List<ShoesEntity>{

    return shoesList.map {

        ShoesEntity(
       id=it.id,
        nombre=it.nombre,
         origen=it.origen,
         image=it.imagenLink,
         marca=it.marca,
         numero=it.numero

            )
    }

}
fun fromInternetToShoesDetailEntity(shoesDetail: ShoesDetail):ShoesDetailEntity{

    return  ShoesDetailEntity(
         id=shoesDetail.id,
     nombre=shoesDetail.nombre,
     origen=shoesDetail.origen,
     image=shoesDetail.imagenLink,
     marca=shoesDetail.marca,
     numero=shoesDetail.numero,
     precio=shoesDetail.precio,
     entrega=shoesDetail.entrega
    )
}