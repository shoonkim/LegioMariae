package com.shoonkim.legiomariae.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun <T> androidLazy(initializer : () -> T) : Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

fun dateTodayWeek(cur : Date) : Int {
    val cal = Calendar.getInstance()
    cal.time = cur
    return cal.get(Calendar.DAY_OF_WEEK)
}

fun fewDaysAgo(cur : Date, days : Int) : Calendar {
    val cal = Calendar.getInstance()
    cal.time = cur
    cal.add(Calendar.DATE, -days)
    return cal
}

fun numTodayWeek(num : Int) : String {

    var str = ""
    when(num){
        1 -> str = "SUN"
        2 -> str = "MON"
        3 -> str = "TUE"
        4 -> str = "WED"
        5 -> str = "THU"
        6 -> str = "FRI"
        7 -> str = "SAT"
    }
    return str
}