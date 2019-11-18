package com.shoonkim.legiomariae

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.week_calender_activity.*
import java.util.*

const val WCATAG:String = "WEEKCALENDERACTIVITY_STATRT"
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

    inner class wca_db_setup() : Thread(){
        @SuppressLint("LongLogTag")
        override fun run() {

            list = FirebaseFirestoreHelper.getWeekCalender(Calendar.getInstance())

        }
    }

    inner class WeekCalenderUpData : AsyncTask<Void, Void, ArrayList<WeekCalenderData>>() {

        override fun doInBackground(vararg p0: Void?): ArrayList<WeekCalenderData>? {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            return FirebaseFirestoreHelper.getWeekCalender(Calendar.getInstance())
        }

        @SuppressLint("LongLogTag")
        override fun onPostExecute(result: ArrayList<WeekCalenderData>?) {
            super.onPostExecute(result)

            Log.d(WCATAG, "onPostExecute")

            if (result != null) {
                list.clear()
                for(tmp in result){
                    list.add(tmp)
                    Log.d(WCATAG, "${tmp.day} => ${tmp.liturgicalDay}")
                }
                wca_adapter!!.notifyDataSetInvalidated()
            }
        }
    }


    @SuppressLint("LongLogTag")
    fun initList(){
        /*
        val instance = Calendar.getInstance()
        val curYear = instance.get(Calendar.YEAR).toString()
        val curMonth = (instance.get(Calendar.MONTH).toInt() + 1).toString()
        val curDay = instance.get(Calendar.DATE).toString()
        val carWeek = instance.get(Calendar.DAY_OF_WEEK).toString()


        Log.d(WCATAG, curYear + ":" + curMonth +":"+curDay+":"+carWeek)
         */

        //val bd_setup = wca_db_setup()
        //bd_setup.start()
        WeekCalenderUpData().execute()
    }
}
