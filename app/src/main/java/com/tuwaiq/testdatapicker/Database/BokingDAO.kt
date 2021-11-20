package com.tuwaiq.testdatapicker.Database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BokingDAO {

    @Query("SELECT * FROM BookingData ")
    fun getAllList():LiveData<List<BookingData>>

    @Query("SELECT * FROM BookingData WHERE id=(:id)")
    fun getListById(id:Int): LiveData<BookingData?>

    @Update
    fun updateList(info:BookingData)

    @Insert
    fun insertList(info:BookingData)


}