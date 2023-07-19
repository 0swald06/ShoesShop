package com.example.shoesshop

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shoesshop.Model.Local.Database.ShoesDatabase
import com.example.shoesshop.Model.Local.Database.ShoesEntity
import com.example.shoesshop.Model.Local.ShoesDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk=[Build.VERSION_CODES.Q], manifest=Config.NONE)
class ShoesDaoTest {


    private lateinit var phonesDaoTest: ShoesDao
    private lateinit var db:ShoesDatabase

    @Before
    fun setUp(){
        val context= ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,ShoesDatabase::class.java).build()
        phonesDaoTest=db.getShoesDao()
    }


    @After
    fun shutDown(){

        db.close()
    }

    @Test
    fun insertPhonesList()= runBlocking {
//Given
        val phonesEntity= listOf(

            ShoesEntity("52","prueba1","1000","url","sd","43"),
            ShoesEntity("42","prueb21","1000","url","sd","43"),
        )
        //When
        phonesDaoTest.insertAllShoes(phonesEntity)

        val phonesLiveData=phonesDaoTest.getAllShoes()
        val phonesList=phonesLiveData.value?: emptyList()

        //Then
        MatcherAssert.assertThat(phonesList, CoreMatchers.not(emptyList()))
        MatcherAssert.assertThat(phonesList.size, CoreMatchers.equalTo(2))

    }
}