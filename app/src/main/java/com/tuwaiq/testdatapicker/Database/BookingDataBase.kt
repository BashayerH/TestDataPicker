package com.tuwaiq.testdatapicker.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [BookingData::class],version = 1)
@TypeConverters(BookingTypeConverter::class)


abstract class BookingDataBase : RoomDatabase() {

    abstract fun bookingDao(): BokingDAO
}