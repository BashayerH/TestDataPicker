package com.tuwaiq.testdatapicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tuwaiq.testdatapicker.Database.BookingData
import com.tuwaiq.testdatapicker.Database.BookingRepo

class BookingViewModel:ViewModel() {


    private val infoRepo= BookingRepo.get()
    private val infIdoLiveData=MutableLiveData<Int>()

    var infoLiveData:LiveData<BookingData?> = Transformations.switchMap(infIdoLiveData){
        infoRepo.getListById(it)
    }

    fun added(info:BookingData){
        infoRepo.insertList(info)
    }

    fun saveUpdate(info: BookingData){
        infoRepo.updateList(info)
    }
}