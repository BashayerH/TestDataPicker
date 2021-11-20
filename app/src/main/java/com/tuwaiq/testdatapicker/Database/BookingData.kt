package com.tuwaiq.testdatapicker.Database

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class BookingData(
    @PrimaryKey (autoGenerate = true)
    var id:Int=0,
    var names:String=" ",
    var title: String=" ",
    var date:Date= Date()
)
