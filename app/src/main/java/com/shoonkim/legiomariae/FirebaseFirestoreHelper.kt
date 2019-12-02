package com.shoonkim.legiomariae

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.shoonkim.legiomariae.data.WCItem
import java.util.*
import kotlin.collections.ArrayList

const val TAG:String = "FirebaseFirestoreHelper"

object FirebaseFirestoreHelper{

    private var db : FirebaseFirestore? = null

    fun initFF(){
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db!!.firestoreSettings = settings
    }

    fun isFirebaseFirestore(): Boolean {
        return db != null
    }

    fun getWeekCalender( today : Calendar): ArrayList<WCItem>{
        val list  = arrayListOf<WCItem>()
        val curMonth = today.get(Calendar.YEAR).toString() + (today.get(Calendar.MONTH).toInt() + 1).toString()
        val DocumentRef = db!!.collection("Calender")
            .document(curMonth)

        var isRun : Boolean = true

        DocumentRef.collection("Day")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list.add(
                        WCItem(
                            document.id, "",
                            document.getString("LiturgicalDay")!!,
                            document.getString("toDayGospel")!!,
                            ""
                        )
                    )
                    //Log.d(TAG, "${document.id} => ${document.data}")
                }
                isRun = false
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                isRun = false
            }

        while(isRun){ }

        /*if (list != null) {
            for(tmp in list){
                Log.d(TAG, "** ${tmp.day} => ${tmp.liturgicalDay}")
            }
        }*/
        return list
    }
}
