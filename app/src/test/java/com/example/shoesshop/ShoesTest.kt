package com.example.shoesshop

import com.example.shoesshop.Model.Local.Database.ShoesDetailEntity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class ShoesTest {

    private lateinit var shoes:ShoesDetailEntity

    @Before
    fun setUp(){

        val id="1"
        val nombre="zapato"
        val origen="extrajero"
        val image="url"
        val marca="chile"
        val numero="40"
        val precio="1000"
        val entrega="false"

        shoes= ShoesDetailEntity(
            id=id,
            nombre=nombre,
            origen=origen,
            image=image,
            marca=marca,
            numero=numero,
            precio=precio,
            entrega=entrega
        )
    }

    @After
    fun tearDown(){
        // para limpiar si es necesario
    }

    @Test
    fun testShoesConstructor(){

        assertEquals("1",shoes.id)
        assertEquals("zapato",shoes.nombre)
        assertEquals("extrajero",shoes.origen)
        assertEquals("url",shoes.image)
        assertEquals("chile",shoes.marca)
        assertEquals("40",shoes.numero)
        assertEquals("1000",shoes.precio)
        assertEquals("false",shoes.entrega)


    }


}