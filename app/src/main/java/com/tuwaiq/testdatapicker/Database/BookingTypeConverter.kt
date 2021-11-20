package com.tuwaiq.testdatapicker.Database

import androidx.room.TypeConverter
import java.util.*

class BookingTypeConverter {

    @TypeConverter
    fun fromDate(date:Date?):Long?{
        return date?.time
    }
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }

//    @TypeConverter
//    fun fromId(id: Int?): String? {
//        return id?.toString()
//    }
//    @TypeConverter
//    fun toID(id: String?): Int? {
//        return Int.fromString(id)
//    }
}