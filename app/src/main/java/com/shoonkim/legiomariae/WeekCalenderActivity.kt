package com.shoonkim.legiomariae

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import com.shoonkim.legiomariae.data.WCItem
import com.shoonkim.legiomariae.ui.WCAFragment
import java.util.*

const val WCATAG:String = "WEEKCALENDERACTIVITY_STATRT"
const val WEEKCALENDERACTIVITY_STATRT = 101

class WeekCalenderActivity : AppCompatActivity() {

    var list = arrayListOf<WCItem>()
    var wca_adapter : WeekCalenderListAdapter? = null
    var wcaHandler : WCAHandler? = null

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week_calender_activity)

        if(savedInstanceState == null){
            changeFragment(WCAFragment())

            Log.d(WCATAG, " changeFragment")
        }
        wcaHandler = WCAHandler()
        wcaHandler?.sendEmptyMessage(WEEKCALENDERACTIVITY_STATRT)
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.wca_base_content, f)
        ft.addToBackStack(null)
        ft.commit()
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

    inner class WeekCalenderUpData : AsyncTask<Void, Void, ArrayList<WCItem>>() {

        override fun doInBackground(vararg p0: Void?): ArrayList<WCItem>? {
            return FirebaseFirestoreHelper.getWeekCalender(Calendar.getInstance())
        }

        @SuppressLint("LongLogTag")
        override fun onPostExecute(result: ArrayList<WCItem>?) {
            super.onPostExecute(result)

            Log.d(WCATAG, "onPostExecute")

            if (result != null) {
                list.clear()
                for(tmp in result){
                    list.add(tmp)
                    Log.d(WCATAG, "${tmp.day} => ${tmp.liturgicalDay}")
                }
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
