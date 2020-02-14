package com.shoonkim.legiomariae.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.shoonkim.legiomariae.MainActivity
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.ui.RxBaseFragment
import com.shoonkim.legiomariae.utils.inflate
import kotlinx.android.synthetic.main.login.*

class Login  : RxBaseFragment(), View.OnClickListener {

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.login)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_google.setOnClickListener(this)
        signupbtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        when(v?.id){
            R.id.login_google->onLoginGoogle()
            R.id.signupbtn -> (activity as MainActivity).changeFragment(SignUp())
        }
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            RC_SIGN_IN->{
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                if(result.isSuccess){
                    Log.d("Firebase_Auth", "google sign ok")
                    //result.signInAccount.email
                }else{
                    Log.d("Firebase_Auth", "google sign not ok")
                }
            }
        }
    }

    fun onLoginGoogle(){

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient((activity as MainActivity), gso)
        auth = FirebaseAuth.getInstance()

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

}