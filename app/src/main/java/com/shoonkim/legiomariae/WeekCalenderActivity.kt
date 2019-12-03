package com.shoonkim.legiomariae

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.shoonkim.legiomariae.ui.WCAFragment

const val WCATAG:String = "WEEKCALENDERACTIVITY_STATRT"

class WeekCalenderActivity : AppCompatActivity() {

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.week_calender_activity)

        if(savedInstanceState == null){
            changeFragment(WCAFragment())

            Log.d(WCATAG, " changeFragment")
        }
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.wca_base_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }
}