package com.shoonkim.legiomariae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shoonkim.legiomariae.ui.WAC_Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            changeFragment(WAC_Fragment())
        }
    }

    private fun changeFragment(f: WAC_Fragment, cleanStact : Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.base_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }
}
