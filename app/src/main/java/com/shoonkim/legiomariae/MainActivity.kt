package com.shoonkim.legiomariae

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var maHandler : MainHandler

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_activity_report_test.setOnClickListener {

            if(FirebaseFirestoreHelper.isFirebaseFirestore()) {
                Log.w(WCATAG, "call WeekCalanderActivity")
                val intent = Intent(this, WeekCalenderActivity::class.java)
                startActivity(intent)
            }else{
                Log.w(WCATAG, "FirebaseFirestore is null")
            }
        }

        maHandler = MainHandler()
        maHandler.sendEmptyMessage(HandlerMsg.APP_START.V)
        MsgLooper.sendEmptyMessage(HandlerMsg.APP_START.V)
    }

    inner class MainHandler : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){
                HandlerMsg.APP_START.V -> init()
            }
        }
    }

    fun init(){
        FirebaseFirestoreHelper.initFF()
    }

}
