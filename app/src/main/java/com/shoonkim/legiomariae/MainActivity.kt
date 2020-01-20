package com.shoonkim.legiomariae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        btn_activity_report_test.setOnClickListener {
            val intent = Intent(this, WeekCalenderActivity::class.java)
            startActivity(intent)
        }
         */
    }
}
