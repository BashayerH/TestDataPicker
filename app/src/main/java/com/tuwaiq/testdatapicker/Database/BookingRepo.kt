package com.tuwaiq.testdatapicker.Database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.concurrent.Executors

const val NAME_DATABASE="database"

class BookingRepo private constructor(context: Context){

   private val dataBase:BookingDataBase= Room.databaseBuilder(
       context.applicationContext,
       BookingDataBase::class.java,
        NAME_DATABASE
   ).build()

    private val executer =Executors.newSingleThreadExecutor()
    private val infoDAO= dataBase.bookingDao()

    fun getAllList(): LiveData<List<BookingData>> =infoDAO.getAllList()

    fun getListById(id:Int): LiveData<BookingData?>{
        return infoDAO.getListById(id)
    }

    fun updateList(info:BookingData){
        executer.execute {
            infoDAO.updateList(info)
        }
    }

    fun insertList(info:BookingData){
        executer.execute {
            infoDAO.insertList(info)
        }
    }




    companion object{
        var INSTANCE:BookingRepo?= null

        fun initialize(context:Context){
            if (INSTANCE==null){
                INSTANCE= BookingRepo(context)
            }
        }

        fun get():BookingRepo{
            return INSTANCE?:
            throw IllegalStateException("repo must be initialize")
        }
    }

}