package com.shoonkim.legiomariae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.shoonkim.legiomariae.ui.RxBaseFragment
import com.shoonkim.legiomariae.ui.WAC_Fragment
import com.shoonkim.legiomariae.ui.user.Login
import com.shoonkim.legiomariae.ui.user.SignUp
import com.shoonkim.legiomariae.utils.checkCurrentUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userEmail : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        userEmail = checkCurrentUser()

        if(savedInstanceState == null){
            changeFragment(WAC_Fragment())
        }

        /*

        Log.d("Firebase_Auth", userEmail)

        if(userEmail == "null"){
            changeFragment(SignUp())
            Log.d("Firebase_Auth", "T")
        } else {
            changeFragment(WAC_Fragment())
            Log.d("Firebase_Auth","F")
        }
         */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.tb_login -> {
                Log.d("Firebase_Auth", "start")
                changeFragment(Login())
                return true}
            else->{return super.onOptionsItemSelected(item)}
        }
    }

    public fun changeFragment(f: RxBaseFragment, cleanStact : Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.base_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }
}
