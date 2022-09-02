package com.example.androidjetpack.util

import java.text.SimpleDateFormat

class DateFormatter {

    companion object{

        private const val DATE_FORMAT = "dd-MM-yyyy"
        private const val DATE_AND_TIME_FORMAT = "dd.MM.yy  HH:mm:ss"
        private const val TIME_FORMAT_HOURS_MINUTES_SECONDS = "HH:mm:ss"
        private const val TIME_FORMAT_MINUTES_SECONDS = "mm:ss"

        fun Long.millisToDate(): String{
            val formatter = SimpleDateFormat(DATE_FORMAT)
            return formatter.format(this)
        }

        fun String.dateToMillis(): Long{
            val formatter = SimpleDateFormat(DATE_FORMAT)
            return formatter.parse(this)!!.time
        }

        fun Long.millisToDateAndTime(): String{
            val formatter = SimpleDateFormat(DATE_AND_TIME_FORMAT)
            return formatter.format(this)
        }

        fun Long.millisToTimeInHourMinutesSeconds(): String{
            val formatter = SimpleDateFormat(TIME_FORMAT_HOURS_MINUTES_SECONDS)
            return formatter.format(this)
        }

        fun Long.millisToTimeInMinutesSeconds(): String{
            val formatter = SimpleDateFormat(TIME_FORMAT_MINUTES_SECONDS)
            return formatter.format(this)
        }

    }
}