package com.tuwaiq.testdatapicker

import androidx.lifecycle.ViewModel
import com.tuwaiq.testdatapicker.Database.BookingData
import com.tuwaiq.testdatapicker.Database.BookingRepo

class ListFragmentViewModel:ViewModel() {

   private val infoRepo=BookingRepo.get()
    val liveDataInfo =infoRepo.getAllList()

    fun insertList(info:BookingData){
        infoRepo.insertList(info)
    }

}