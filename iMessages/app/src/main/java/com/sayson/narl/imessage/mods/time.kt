package com.sayson.narl.imessage.mods

import java.text.SimpleDateFormat
import java.util.*


class time {

    fun getCurrentTime():Date {
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("HH:mm:ss")
        val strDate =  calendar.time
        return strDate
    }
}