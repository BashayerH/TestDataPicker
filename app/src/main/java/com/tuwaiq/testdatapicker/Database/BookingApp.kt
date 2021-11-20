package com.tuwaiq.testdatapicker.Database

import android.app.Application

class BookingApp :Application(){
    override fun onCreate() {
        super.onCreate()
        BookingRepo.initialize(this)
    }
}