package com.shoonkim.legiomariae.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shoonkim.legiomariae.R
import com.shoonkim.legiomariae.ui.RxBaseFragment
import com.shoonkim.legiomariae.utils.inflate

class SignUp  : RxBaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.signup)
    }
}