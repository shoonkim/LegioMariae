package com.shoonkim.legiomariae.utils

import com.google.firebase.auth.FirebaseAuth

fun checkCurrentUser() : String {
    return FirebaseAuth.getInstance().currentUser?.email.toString()
}