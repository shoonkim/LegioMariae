package com.shoonkim.legiomariae

import android.os.Handler
import android.os.Message
import android.util.Log

object MsgLooper : Handler() {

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)

        when(msg.what){
            HandlerMsg.APP_START.V ->  Log.w("MsgLooper", "${HandlerMsg.APP_START.V }")
        }
    }
}