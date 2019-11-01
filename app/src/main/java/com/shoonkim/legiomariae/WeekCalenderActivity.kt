package com.shoonkim.legiomariae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.week_calender_activity.*
import java.util.*

const val TAG:String = "WEEKCALENDERACTIVITY_STATRT"
const val WEEKCALENDERACTIVITY_STATRT = 101

class WeekCalenderActivity : AppCompatActivity() {

    var list = arrayListOf<WeekCalenderData>()
    var wca_adapter : WeekCalenderListAdapter? = null
    var wcaHandler : WCAHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week_calender_activity)

        wca_adapter = WeekCalenderListAdapter(this, list)
        week_calender_list_view.adapter = wca_adapter

        wcaHandler = WCAHandler()

        wcaHandler?.sendEmptyMessage(WEEKCALENDERACTIVITY_STATRT)
    }

    inner class WCAHandler : Handler(){

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){
                101 -> initList()
            }
        }
    }

    fun initList(){

        val instance = Calendar.getInstance()
        val curYear = instance.get(Calendar.YEAR).toString()
        val curMonth = instance.get(Calendar.MONTH).toString()
        val curDay = instance.get(Calendar.DATE).toString()
        val carWeek = instance.get(Calendar.DAY_OF_WEEK).toString()

        Log.d(TAG, curYear + ":" + curMonth +":"+curDay+":"+carWeek)
    }

}
